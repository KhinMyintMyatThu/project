<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Photo"%>

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
		<br>
			<a href="photostreams.jsp" style="float: left">Back to
				photostreams</a><a href="taggedphotostreams.jsp" style="float: right">Back
				to tagged photostreams</a> <br> <br>
			<div class="container w3-center">
				<div class="w3-bar">
					<div class="w3-bar-item">
						<i class="fa fa-user-circle w3-xlarge w3-padding-16"></i>
					</div>
					<div class="w3-bar-item w3-padding-16">
						<h4>
							<%=request.getSession(false).getAttribute("username")%>
						</h4>
					</div>
				</div>
			</div>

			<div class="container w3-center"
				style="padding-left:250px;  width: 4000px; height: auto; border-spacing: 2px;">
				<div class="w3-row-padding w3-center">



					<div
						class="w3-col w3-center s6 w3-border w3-card w3-round-xlarge w3-padding-large "
						style="column-gap: 5px; width: 350px;">

						<img class="w3-hover-shadow" id="mainImage"
							src="DisplayImage?photoid=<%=request.getSession(false).getAttribute("selectedphotoid")%>"
							width="250" height="250" />
					</div>

					<div class="w3-col s6 w3-rest w3-row-padding"
						style="column-gap: 5px; width: 400px;">
							<%
								Photo photo=new Photo();		
								photo=(Photo)request.getSession(false).getAttribute("photodetail");
								List<String>  tagPeopleList=new ArrayList<String>();
								tagPeopleList=(ArrayList<String>)request.getSession(false).getAttribute("tagpeoplelist");
							%>
							<table style="align-content: center" class="photodata">
							<tr height="50px">
								<td style="foat:right width:250px">Photo Description :</td>&nbsp;
								<td><%=photo.getDescription() %></td>
							</tr>
							<tr height="50px">
								<td style="foat:right width:250px">Photo Privacy :</td>
								<td><%=photo.getPrivacy() %></td>
							</tr>
							<tr height="50px">
								<td style="foat:right width:250px">Created time :</td>
								<td><%=photo.getCreated_time() %></td>
							</tr>
							<tr height="50px">
								<td style="foat:right width:250px">People in this photo :</td>
								
								</td>	
									<%for(int i=0; i<tagPeopleList.size(); i++){ %>
										<table>	
											<tr style="float: left">
												
												<%=tagPeopleList.get(i) %>							
											</tr>
										</table>	
									<%} %>
								</td>
								
							</tr>
							
							</table>
					</div>


				</div>

			</div>

		</div>
	</div>
</body>

</html>