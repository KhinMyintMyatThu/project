<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="service.ExploreFriendsService"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mysql.fabric.xmlrpc.base.Array"%>
<%@page import="java.util.HashMap"%>
<%@page import="entity.User"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="dbconnection.Dbconnection"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Image Archival System</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/upload.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/upload.js"></script>
<link rel="stylesheet" href="css/chosen.css" />
<script type="text/javascript" src="js/chosen.min.js"></script>
<script type="text/javascript" src="js/chosen.jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(e) {
		$(".names").chosen({
			width : "100%"
		});
	});
</script>

</head>
<body>
	<div id="main_container">
		<div id="header">
			<a href="photostreams.jsp" style="text-decoration: none;">
				<div id="logo">Image Archival System</div>
			</a>
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
													style="width: 200px; height: 40px; font-size: 10px; font-weight: lighter;">
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

							<td width="80px;"><i class="fa fa-upload w3-xlarge"></i></td>
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

		<div id="image_body">
			<form action="Upload" method="post" enctype="multipart/form-data">
				<div id="image_table">
					<table id="dynamic_img">
						<tr>
							<td><button name="add_img" id="add_img"
									class="w3-button w3-light-gray">
									<i class="fa fa-plus"></i>&nbsp;Add Photo
								</button></td>
						</tr>
					</table>
				</div>
				<div id="description_table">
					<table id="description">
						<tbody>
							<tr>
								<th>Description</th>
							</tr>
							<tr>
								<td>Add description</td>
								<td><input type="text" name="description" required
									placeholder="Describe something about photo"></td>
							</tr>
							<tr>
								<th><hr>Tag with keywords</th>
							</tr>
							<tr></tr>
							<tr>
								<td>Tag</td>
								<td><input type="text" name="tagkeywords" required
									placeholder="Separate tags with space">
									<span style="float: right; font-size: 10px">At most 3 words is recommended.</span></td>
									
							</tr>
							<tr>
								<th><hr>Share with friends</th>
							</tr>
							<tr>
								<td>People</td>
								<td><div>
										<%
											
											User user = (User) request.getSession(false).getAttribute("user");
											HashMap<Integer, String> followers = new HashMap<Integer, String>();
											HashMap<Integer, String> followings = new HashMap<Integer, String>();
											ExploreFriendsService service=new ExploreFriendsService();
											
											followers=service.selectFollowers(user.getUserId());
											followings=service.selectFollowings(user.getUserId());
										%>
										<select class="names" multiple data-placeholder="Tag people"
											name="names">
											<option value=" "></option>
											<%
												for (Iterator<Map.Entry<Integer, String>> follower = followers.entrySet().iterator(); follower.hasNext();) {
													Map.Entry<Integer, String> entryFollower = follower.next();
													for (Iterator<Map.Entry<Integer, String>> following = followings.entrySet().iterator(); following
															.hasNext();) {
														Map.Entry<Integer, String> entryFollowing = following.next();
														if (entryFollower.getKey() == entryFollowing.getKey()) {
											%>

											<option value="<%=entryFollower.getKey()%>"><%=entryFollower.getValue()%></option>

											<%
												break;
														}
													}
												}
											%>

										</select>
									</div></td>
							</tr>
							<tr>
								<th><hr>Privacy Setting</th>
							</tr>
							<tr>
								<td><input type="radio" name="privacy" value="public"
									required selected></td>
								<td>Share to public</td>
							</tr>
							<tr>
								<td><input type="radio" name="privacy" value="private"></td>
								<td>Only you</td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center"><br> <input
									type="submit" value="Done" style="padding: 10px 20px;" /></td>

							</tr>
						</tbody>
					</table>
				</div>
			</form>

		</div>

	</div>

</body>
</html>