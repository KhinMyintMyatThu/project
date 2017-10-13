package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import entity.Keyword;
import entity.Photo;
import entity.User;
import service.UploadService;

/**
 * Servlet implementation class Upload1
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "E:/";
	private Photo photo = new Photo();
	private User user = new User();
	private Keyword keyword = new Keyword();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UploadService service=new UploadService();
		List<Integer> insertedPhotoId = new ArrayList<>();
		List<Integer> insertedKeywordId = new ArrayList<>();
		List<File> photoList = new ArrayList<>();
		List<String> content = new ArrayList<>();
		List<Integer> taggedPeopleId = new ArrayList<>();
		user = (User) request.getSession(false).getAttribute("user");

		try {
			List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory())
					.parseRequest(new ServletRequestContext(request));

			for (FileItem item : multiparts) {
				if (!item.isFormField()) {
					String name = new File(item.getName()).getName();
					item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
					File file = new File(UPLOAD_DIRECTORY + File.separator + name);

					photoList.add(file);
				}
				if (item.isFormField()) {
					if (item.getFieldName().equals("names")) {
						taggedPeopleId.add(Integer.parseInt(item.getString()));
					} else {
						content.add(item.getString());
					}
				}
			}

			photo.setPhoto(photoList);
			photo.setDescription(content.get(0));
			photo.setPrivacy(content.get(2));

			keyword.setKeywords(settingKeywords(content.get(1)));

		} catch (Exception ex) {
			System.out.println("File Upload Failed due to " + ex);

		}

		try {
			/**
			 * Inserting photo data
			 */
			for (int i = 0; i < photo.getPhoto().size(); i++) {
				service.insertPhoto(photo.getPhoto().get(i), photo.getDescription(), photo.getPrivacy(),
						user.getUserId());
				insertedPhotoId.add(service.lastInsertedPhoto());
			}
			photo.setPhotoId(insertedPhotoId);
			
			/**
			 * Check the inserted keyword is already existing or not
			 */
			for (int i = 0; i < keyword.getKeywords().size(); i++) {
				int keywordCount = service.searchExistingKeyword(keyword.getKeywords().get(i));

				if (keywordCount == 0) {
					service.insertKeyword(keyword.getKeywords().get(i));

					insertedKeywordId.add(service.lastInsertedKeywordId());

				} else {
					insertedKeywordId.add(service.existingKeywordId(keyword.getKeywords().get(i)));
				}
			}
			keyword.setKeywordId(insertedKeywordId);

			/**
			 * Insert data into photo_keyword_map table
			 */
			for (int i = 0; i < photo.getPhoto().size(); i++) {

				for (int j = 0; j < keyword.getKeywords().size(); j++) {

					int photoKeywordCount = service.checkExistingPhotoKeyword(photo.getPhotoId().get(i),
							keyword.getKeywordId().get(j));

					if (photoKeywordCount == 0) {
						service.insertPhotoKeywordMap(photo.getPhotoId().get(i), keyword.getKeywordId().get(j),
								keyword.getKeywords().get(j));
					}
				}
			}

			/**
			 * Insert data into tagged_people_table
			 */
			for (int i = 0; i < taggedPeopleId.size(); i++) {
				for (int j = 0; j < photo.getPhotoId().size(); j++) {

					service.insertTagPeople(photo.getPhotoId().get(j), user.getUserId(), taggedPeopleId.get(i));

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("photostreams.jsp");

	}

	public List<String> settingKeywords(String keywordString) throws IOException {
		String[] word = keywordString.split("\\s+");
		List<String> keyword = new ArrayList<>();
		for (String e : word) {
			keyword.add(e);
		}
		return keyword;
	}

}
