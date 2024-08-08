package com.leet.code.dao;

import com.leet.code.model.Response;
import com.leet.code.model.UserModel;
import com.leet.code.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import java.sql.*;

@Component
public class UserDAO implements UserService {

	Response res = new Response();

	String url = "jdbc:postgresql://127.0.0.1:5433/leetcode";
	String username = "postgres";
	String password = "pavi";

	@Override
	public Response createUser(UserModel model) {

		try {
			Class.forName("org.postgresql.Driver");

			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {

				String createUser = "INSERT INTO user_details(\r\n" + "	 email, password, user_name)\r\n"
						+ "	VALUES ('" + model.getEmail() + "', '" + model.getPassword() + "', '" + model.getUserName()
						+ "');";

				System.out.println(createUser);
				st.executeUpdate(createUser);

				res.setResponseCode(200);

				res.setResponseMsg("success");
				res.setData("User Created Successfully!");
				res.setjData(null);

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Cannot Create User!");
				res.setjData(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setResponseCode(500);
			res.setResponseMsg("error");
			res.setData("Driver Class Error!");
			res.setjData(null);
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getAllUsers() {

		try {
			Class.forName("org.postgresql.Driver");

			String getAllUsers = "SELECT * FROM user_details";

			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(getAllUsers);) {

				JSONArray jsonArray = new JSONArray();

				while (rs.next()) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("user_id", rs.getInt("user_id"));
					jsonObj.put("email", rs.getString("email"));
					jsonObj.put("password", rs.getString("password"));
					jsonObj.put("user_name", rs.getString("user_name"));

					jsonArray.add(jsonObj);
				}

				res.setResponseCode(200);
				res.setResponseMsg("success");
				res.setData("User Fetched Successfully!");
				res.setjData(jsonArray);

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Cannot Create User!");
				res.setjData(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setResponseCode(500);
			res.setResponseMsg("error");
			res.setData("Driver Class Error!");
			res.setjData(null);
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getUserById(Long Id) {

		try {
			Class.forName("org.postgresql.Driver");

			String getOneUser = "SELECT * FROM user_details WHERE user_id = '" + Id + "';";

			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(getOneUser);) {

				JSONObject jsonObj = new JSONObject();

				while (rs.next()) {

					jsonObj.put("user_id", rs.getInt("user_id"));
					jsonObj.put("email", rs.getString("email"));
					jsonObj.put("password", rs.getString("password"));
					jsonObj.put("user_name", rs.getString("user_name"));

				}

				res.setResponseCode(200);
				res.setResponseMsg("success");
				res.setData("User Fetched Successfully!");
				res.setjData(jsonObj);

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Cannot Fetch User!");
				res.setjData(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setResponseCode(500);
			res.setResponseMsg("error");
			res.setData("Driver Class Error!");
			res.setjData(null);
		}
		return res;
	}

	@Override
	public Response updateUser(UserModel model) {

		try {
			Class.forName("org.postgresql.Driver");

			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {

				String updateUser = "UPDATE user_details SET password = '" + model.getPassword() + "',user_name = '"
						+ model.getUserName() + "',email = '" + model.getEmail() + "'" + "WHERE user_id = "
						+ model.getUserId() + ";";

				System.out.println(updateUser);
				st.executeLargeUpdate(updateUser);

				res.setResponseCode(200);
				res.setResponseMsg("success");
				res.setData("User Updated Successfully!");
				res.setjData(null);

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Cannot Update User!");
				res.setjData(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setResponseCode(500);
			res.setResponseMsg("error");
			res.setData("Driver Class Error!");
			res.setjData(null);
		}
		return res;
	}
	
	
	@Override
	public Response deleteUser(String userId) {

		try {
			Class.forName("org.postgresql.Driver");

			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {

				String deleteUser = "DELETE FROM user_details WHERE user_id = '" + userId + "';";

				System.out.println(deleteUser);
				st.executeUpdate(deleteUser);

				res.setResponseCode(200);
				res.setResponseMsg("success");
				res.setData("User Deleted Successfully!");
				res.setjData(null);

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Cannot Delete User!");
				res.setjData(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setResponseCode(500);
			res.setResponseMsg("error");
			res.setData("Driver Class Error!");
			res.setjData(null);
		}

		return res;
	}

}
