package vn.tasksmanagement.converter;

import java.sql.ResultSet;
import java.sql.SQLException;

import vn.tasksmanagement.enums.UserRole;
import vn.tasksmanagement.models.User;

public class UserConverter implements Converter<User> {
  // Static fields:
  private static UserConverter instance;

  // Static methods:
  public static UserConverter getInstance() {
    if (instance == null) {
      instance = new UserConverter();
    }

    return instance;
  }

  // Constructors:
  private UserConverter() {
  }

  // Methods:
  @Override
  public User convert(ResultSet table) throws SQLException {
    // Lấy tất cả dữ liệu trên dòng (lấy theo từng ô)
    String username = table.getString("username");
    String password = table.getString("password");
    String fullName = table.getString("fullName");
    String roleStr = table.getString("role");

    // Chuyển đổi chuỗi role sang enum role
    UserRole role = UserRole.valueOf(roleStr);

    // Tạo đối tượng User từ dữ liệu đã có
    return User.builder()
        .username(username)
        .password(password)
        .fullName(fullName)
        .role(role)
        .build();
  }
}
