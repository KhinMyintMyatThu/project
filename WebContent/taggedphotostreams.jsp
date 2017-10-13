<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="service.OtherServices"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="dbconnection.Dbconnection"%>
<%@page import="entity.User"%>
<%@page import="java.util.List"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Image Archival System</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="css/home.css">
</head>
<body>
	<div id="main_container">
		<div id="header">
			<a href="photostreams.jsp" style="text-decoration: none;">
				<div id="logo">Image Archival System</div></a>
			<div class="search_form">
				<table>
					<tbody>
						<tr>
							<td>
								<div class="w3-bar" style="width: 300px;">
									<div class="w3-row-padding" style="width: 300px;">
										<form method="post" action="Search">
											<div class="w3-col s7">
												<input type="text" class="w3-input w3-border"
													placeholder="Search photo by keyword" name="search"
													style="width: 200px; height: 40px; font-size: 10px; font-weight: lighter;"
													>
											</div>
											<div class="w3-col s5">
												<button type="submit" style="width: 50px; height: 40px;"
													class="w3-button w3-border w3-light-gray"
													name="search_submit">
													<i class="fa fa-search"></i>
												</button>
											</div>
										</form>
									</div>

								</div>
							</td>
							<td width="80px;"><a style="text-decoration: none"
								href="ExploreFriends" title="Explore Friends"><i class="fa fa-bandcamp w3-xlarge"></i></a></td>
							<td width="80px;"><a style="text-decoration: none"
								href="upload.jsp" title="Upload"><i class="fa fa-upload w3-xlarge"></i></a></td>
							<td width="60px;"><i class="fa fa-user-circle w3-xlarge"></i>
							</td>
							<td>${user.firstName}</td>
							<td width="60px;" style="padding-left: 20px;"><a href="Logout" title="Logout" onclick="return confirm('Do you really want to logout?')"><i
								class="fa fa-sign-out w3-xlarge"></i></a></td>
						</tr>
					</tbody>
				</table>

			</div>


		</div>
		<div id="body">
			<div class="w3-container w3-center">
				<div class="w3-bar">
					<table>
						<tbody>
							<tr>
								<td class="w3-button w3-light-gray" style="padding: 10px;"><a
									href="photostreams.jsp"
									class="w3-bar-item w3-button w3-hover-none w3-border-white w3-bottombar w3-hover-border-grey"
									style="text-decoration: none;">Photostreams</a></td>
								<td class="w3-button w3-gray" style="padding: 10px"><a
									href="#"
									class="w3-bar-item w3-button w3-hover-none w3-border-white w3-bottombar w3-hover-border-grey"
									style="text-decoration: none;">Tagged Photostreams</a>
								<td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>

			<div class="container w3-center"
				style="width: 100%; height: auto; border-spacing: 5px;">
				<div class="w3-row-padding w3-padding-large w3-center"
					style="border-spacing: 50px 0;">
					<%
						User user = (User) request.getSession(false).getAttribute("user");
						List<Integer> photoId = new ArrayList<Integer>();
						List<String> description = new ArrayList<String>();
						
						OtherServices service=new OtherServices();
						try {
							
							photoId=service.selectTagPhotoId(user.getUserId());
							description=service.selectTagPhotoDes(user.getUserId());

							for (int i = 0; i < photoId.size(); i++) {
					%>
					<div
						class="w3-col s3  w3-card-4 w3-round-large  w3-padding-large w3-section w3-row-padding"
						style="padding: 10px 0; border-collapse: separate; border-spacing: 10px; height: 400px;">
						<a href="DetailPhoto?photoid=<%=photoId.get(i)%>" style="text-decoration: none"><img class="w3-hover-shadow"
							src="DisplayImage?photoid=<%=photoId.get(i)%>" width="250"
							height="250" /></a>
						<p>
							<%=description.get(i)%>
						</p>
						<table>
							<tr>
						<%
							
							List<String> keywords=service.selectTagPhotoKeywords(photoId.get(i));		
							for(String keyword: keywords){							
						%>
								<td style="font-size: 10px;"><i class="fa fa-tags w3-tiny"></i><%=keyword %></td>
						<%
							} 
						
						%>
						</tr>
						</table>
						<a href="DeletePhoto?photoid=<%=photoId.get(i)%>" onclick="return confirm('Do you want to delete this photo?')"><i class="fa fa-trash w3-large" style="float:right; color:red"></i></a>
					</div>
					<%
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					%>

				</div>
			</div>


		</div>
</body>
</html>