<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entity.User"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Image Archival System</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="css/home.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.btnfollow').click(function(event) {
			if ($(this).hasClass('follow')) {
				$(this).text('Follow').toggleClass('follow');
				$(this).prepend($("<i class='fa fa-plus'></i>")).button();
			} else {
				$(this).text('Following').toggleClass('follow');
				$(this).prepend($("<i class='fa fa-check'></i>")).button();
			}

		});
		$('.btnfollowing').click(function(event) {
			if ($(this).hasClass('following')) {
				$(this).text('Following').toggleClass('following');
				$(this).prepend($("<i class='fa fa-check'></i>")).button();
			} else {
				$(this).text('Follow').toggleClass('following');
				$(this).prepend($("<i class='fa fa-plus'></i>")).button();
			}

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
							<td width="80px;"><i class="fa fa-bandcamp w3-xlarge"></i></td>
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
			<table style="align-self: center; width: 60%;">
				<tr>
					<th style="float: left">Suggested friends</th>
				</tr>
				<%
					HashMap<Integer, String> followers = new HashMap<Integer, String>();
					HashMap<Integer, String> followings = new HashMap<Integer, String>();
					HashMap<Integer, String> newFriends = new HashMap<Integer, String>();

					followers.putAll((HashMap<Integer, String>) request.getSession(false).getAttribute("followers"));
					followings.putAll((HashMap<Integer, String>) request.getSession(false).getAttribute("followings"));
					newFriends.putAll((HashMap<Integer, String>) request.getSession(false).getAttribute("newfriends"));

					User user = (User) request.getSession(false).getAttribute("user");

					Set followerSet = followers.entrySet();
					Iterator followerIterator = followerSet.iterator();

					Set followingSet = followings.entrySet();
					Iterator followingIterator = followingSet.iterator();

					Set newFriendSet = newFriends.entrySet();
					Iterator newFriendIterator = newFriendSet.iterator();

					while (newFriendIterator.hasNext()) {
						Map.Entry newFriend = (Map.Entry) newFriendIterator.next();
						if (newFriend.getKey() != (Integer) user.getUserId()) {
				%>
				<tr>
					<td><%=newFriend.getValue()%></td>
					<td style="width: 30%"><a
						href="MakeFriendship?id=<%=newFriend.getKey()%>&isfollow=follow"
						style="text-decoration: none"><button name="follow"
								class="btnfollow w3-button w3-round-xlarge w3-light-gray"
								style="padding: 10px 10px;" id="<%=newFriend.getKey()%>">
								<i class="fa fa-plus"></i>Follow
							</button></a></td>
				</tr>
				<%
					}
					}
				%>
				
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<th style="float: left;">You followed (<%=followings.size()%>)
						friends
					</th>
				</tr>
				<%
					

					while (followingIterator.hasNext()) {
						Map.Entry following = (Map.Entry) followingIterator.next();
				%>
				<tr>
					<td><%=following.getValue()%></td>
					<td style="width: 30%"><a href="MakeFriendship?id=<%=following.getKey()%>&isfollow=following"><button
							class="btnfollowing w3-button w3-round-xlarge w3-light-gray"
							style="padding: 10px 10px;" id="<%=following.getKey()%>">
							<i class="fa fa-check"></i>Following
						</button></a></td>
				</tr>
				<%
					}
				%>
				<tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<th style="float: left;">(<%=followers.size()%>) friends are
						following you
					</th>
				</tr>
				<%
					for (Iterator<Map.Entry<Integer, String>> follower = followers.entrySet().iterator(); follower.hasNext();) {
						Map.Entry<Integer, String> entryFollower = follower.next();
				%>
				<tr>
					<td><%=entryFollower.getValue()%></td>
				<%	
					}
				%>
				<tr>
			</table>
		</div>

	</div>

</body>
</html>
