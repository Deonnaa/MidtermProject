package com.skilldistillery.quorum.data;

import java.util.List;

import com.skilldistillery.quorum.entities.User;

public interface UserDAO {

	User authenticateUser(String username, String password);

	User createUser(User user);

	User getUserById(int id);

	User getUserById(int id, boolean loadFollows);

	User getUserByUsername(String username);

	void updateUser(int id, User user);

	User update(User user);

	void setEnabled(int id, boolean status);

	void changeRole(int id, String role);

	boolean removeFollower(int userId, int followerId);

	boolean removeFollowing(int followingId, int userId);

	boolean addFollowing(int userId, int followingId);

	List<User> getByGroupId(int groupId);

	List<User> searchByQuery(String query, User user);

	void deleteUser(int id);

	void activateUser(int id);
	
	boolean userIsFollowing(User followed, User following);
	
	User authenticateUserLogin(String username, String password);

}
