<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Image Archival System</title>
<link rel="stylesheet" href="css/index.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<div id="main_container">
		<div id="header">
			<div id="logo">Image Archival System</div>
			<div class="login_form">
				<form action="Login" method="post" id="login_form">
					<table>
						<tbody>
							<tr>
								<td>Email</td>
								<td>Password</td>
							</tr>
							<tr>
								<td><input type="text" placeholder="Email" required name="email"/></td>
								<td><input type="password" placeholder="Password" required name="password"/></td>
								<td><input type="submit" value="Log In" /></td>
								<% String loginMessage = (String)request.getSession(false).getAttribute("message"); 
									if(loginMessage!=null){%>
								<span class="error"><%=loginMessage %></span><%} %>
							</tr>
							
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<div id="body_section" >
			<p style="font-size: 20px; color: #333; font-weight: bold; padding-left: 150px;">
				Share your photos to others and <br>Explore how your beloved
				people are sharing their photos
			</p>
			
			<div id="right">
				<p style="font-size: 36px; color: #333; font-weight: bold;">Registration</p>
				<% 
				String msg = (String)request.getSession(false).getAttribute("error");
				if (msg != null) {
				    %><p style="color:red"><%= msg %></p><%
				}
				%>
				<div id="form">
					<form action="Register" method="post" id="reg_form">
						<table>
							<tr>
								<td><input type="text" style="width: 135px;"
									placeholder="Firstname" name="firstname" required/> <input type="text"
									style="width: 136px;" name="lastname" placeholder="Lastname" required /></td>
							</tr>
							<tr>
								<td><input type="text" style="width: 300px;"
									placeholder="Email" required name="email"/></td>
							</tr>
							<tr>
								<td><input type="password" style="width: 300px;"
									placeholder="New Password" required name="password" id="password"/></td>
							</tr>
							<tr>
								<td><input type="password" style="width: 300px;"
									placeholder="Confirmed Password" required name="confirmedpassword"/></td>
							</tr>
							<tr>
								<td>Birthday</td>
							</tr>
							<tr>
								<td><select name="dob_month" id="dob_month"
									class="input_pulldown">
										<option value="0">Month:</option>
										<option value="01">January</option>
										<option value="02">February</option>
										<option value="03">March</option>
										<option value="04">April</option>
										<option value="05">May</option>
										<option value="06" selected="selected">June</option>
										<option value="07">July</option>
										<option value="08">August</option>
										<option value="09">September</option>
										<option value="10">October</option>
										<option value="11">November</option>
										<option value="12">December</option>
								</select> <select name="dob_day" id="dob_day" class="input_pulldown">
										<option value="0">Select...</option>
										<option value="01">1</option>
										<option value="02">2</option>
										<option value="03">3</option>
										<option value="04">4</option>
										<option value="05" selected="selected">5</option>
										<option value="06">6</option>
										<option value="07">7</option>
										<option value="08">8</option>
										<option value="09">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
										<option value="24">24</option>
										<option value="25">25</option>
										<option value="26">26</option>
										<option value="27">27</option>
										<option value="28">28</option>
										<option value="29">29</option>
										<option value="30">30</option>
										<option value="31">31</option>
								</select> <select name="dob_year" id="dob_year" class="input_pulldown">
										<option value="0">Select...</option>
										<option value="2002">2002</option>
										<option value="2001">2001</option>
										<option value="2000">2000</option>
										<option value="1999">1999</option>
										<option value="1998">1998</option>
										<option value="1997">1997</option>
										<option value="1996">1996</option>
										<option value="1995">1995</option>
										<option value="1994">1994</option>
										<option value="1993">1993</option>
										<option value="1992">1992</option>
										<option value="1991">1991</option>
										<option value="1990">1990</option>
										<option value="1989">1989</option>
										<option value="1988">1988</option>
										<option value="1987">1987</option>
										<option value="1986">1986</option>
										<option value="1985">1985</option>
										<option value="1984">1984</option>
										<option value="1983">1983</option>
										<option value="1982">1982</option>
										<option value="1981">1981</option>
										<option value="1980">1980</option>
										<option value="1979">1979</option>
										<option value="1978">1978</option>
										<option value="1977">1977</option>
										<option value="1976">1976</option>
										<option value="1975">1975</option>
										<option value="1974">1974</option>
										<option value="1973">1973</option>
										<option value="1972">1972</option>
										<option value="1971">1971</option>
										<option value="1970" selected="selected">1970</option>
										<option value="1969">1969</option>
										<option value="1968">1968</option>
										<option value="1967">1967</option>
										<option value="1966">1966</option>
										<option value="1965">1965</option>
										<option value="1964">1964</option>
										<option value="1963">1963</option>
										<option value="1962">1962</option>
										<option value="1961">1961</option>
										<option value="1960">1960</option>
										<option value="1959">1959</option>
										<option value="1958">1958</option>
										<option value="1957">1957</option>
										<option value="1956">1956</option>
										<option value="1955">1955</option>
										<option value="1954">1954</option>
										<option value="1953">1953</option>
										<option value="1952">1952</option>
										<option value="1951">1951</option>
										<option value="1950">1950</option>
										<option value="1949">1949</option>
										<option value="1948">1948</option>
										<option value="1947">1947</option>
										<option value="1946">1946</option>
										<option value="1945">1945</option>
										<option value="1944">1944</option>
										<option value="1943">1943</option>
										<option value="1942">1942</option>
										<option value="1941">1941</option>
										<option value="1940">1940</option>
										<option value="1939">1939</option>
										<option value="1938">1938</option>
										<option value="1937">1937</option>
										<option value="1936">1936</option>
										<option value="1935">1935</option>
										<option value="1934">1934</option>
										<option value="1933">1933</option>
										<option value="1932">1932</option>
										<option value="1931">1931</option>
										<option value="1930">1930</option>
										<option value="1929">1929</option>
										<option value="1928">1928</option>
										<option value="1927">1927</option>
										<option value="1926">1926</option>
										<option value="1925">1925</option>
										<option value="1924">1924</option>
										<option value="1923">1923</option>
										<option value="1922">1922</option>
										<option value="1921">1921</option>
										<option value="1920">1920</option>
										<option value="1919">1919</option>
										<option value="1918">1918</option>
										<option value="1917">1917</option>
								</select></td>
							</tr>
							<tr>
								<td><input type="radio" name="gender" value="Male" >Male
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
									type="radio" name="gender" value="Female">Female</td>
							</tr>
							<tr><td>By clicking Register, you agree to our Terms and Policy</td></tr>
							<tr><td><input type="submit" value="Register" style="padding: 10px 20px;"/></td></tr>
						</table>
					</form>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>