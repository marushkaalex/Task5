package com.epam.am.dao;

import com.epam.am.entity.User;
import com.jolbox.bonecp.BoneCP;
import org.junit.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class H2UserDaoTest {
    public static final String UUID = "10000000-0000-0000-0000-00000000000"; // need one more symbol
    public static final String UUID_TO_FIND = "20000000-0000-0000-0000-000000000000";
    public static final String USERNAME_TO_FIND = "testUsername";
    public static final String EMAIL_TO_FIND = "test@email.com";
    public static final String PASSWORD_TO_FIND = "testPassword";
    public static final String UUID_TO_ADD = "30000000-0000-0000-0000-000000000000";
    public static final String USERNAME_TO_ADD = "addedTestUsername";
    public static final String EMAIL_TO_ADD = "added@email.com";
    public static final String PASSWORD_TO_ADD = "addedTestPassword";
    public static final String ROLE = "CLIENT";
    public static final String INSERT_STATEMENT =
            "INSERT INTO USER VALUES (?,?,?,?,?)";
    public static final String DELETE_STATEMENT =
            "DELETE FROM USER WHERE uuid=?";
    public static final String TO_REMOVE = "toRemove";
    public static final String TO_REMOVE_BY_UUID = "toRemoveByUUID";
    public static final String TO_REMOVE_BY_EMAIL = "toRemoveByEmail";
    public static final String TO_REMOVE_BY_USERNAME = "toRemoveByUsername";
    public static final String TO_BE_UPDATED = "toBeUpdated";
    public static final String TO_UPDATE = "toUpdate";
    private static final User userToFind;
    private static final User userToAdd;
    private static final User userToRemove;
    private static final User userToRemoveByUUID;
    private static final User userToRemoveByEmail;
    private static final User userToRemoveByUsername;
    private static final User userToBeUpdated;
    private static final User userToUpdate;
    private static BoneCP pool;
    private static UserDao userDao = new H2UserDao();
    private static List<User> users;

    static {
        userToFind = new User.Builder().uuid(java.util.UUID.fromString(UUID_TO_FIND))
                .username(USERNAME_TO_FIND)
                .email(EMAIL_TO_FIND)
                .password(PASSWORD_TO_FIND)
                .role(User.Role.CLIENT)
                .build();

        userToAdd = new User.Builder().uuid(java.util.UUID.fromString(UUID_TO_ADD))
                .username(USERNAME_TO_ADD)
                .email(EMAIL_TO_ADD)
                .password(PASSWORD_TO_ADD)
                .role(User.Role.CLIENT)
                .build();

        userToRemoveByUUID = createUser(UUID + 0, TO_REMOVE_BY_UUID);
        userToRemove = createUser(UUID + 1, TO_REMOVE);
        userToRemoveByEmail = createUser(UUID + 2, TO_REMOVE_BY_EMAIL);
        userToRemoveByUsername = createUser(UUID + 3, TO_REMOVE_BY_USERNAME);
        userToBeUpdated = createUser(UUID + 4, TO_BE_UPDATED); //to update user there must be the same UUID
        userToUpdate = createUser(UUID + 4, TO_UPDATE);

        users = new ArrayList<>();
        users.add(userToFind);
        users.add(userToRemove);
        users.add(userToRemoveByUUID);
        users.add(userToRemoveByEmail);
        users.add(userToRemoveByUsername);
        users.add(userToBeUpdated);
    }

    private static User createUser(String UUID, String name) {
        return new User.Builder().uuid(java.util.UUID.fromString(UUID))
                .username(name)
                .email(name)
                .password(name)
                .role(User.Role.CLIENT)
                .build();
    }

    @BeforeClass
    public static void oneTimeSetUp() throws SQLException {

        for (User user : users) {
            userDao.add(user);
        }

        assertTrue(userDao.getUserList().size() >= 6);
    }

    @AfterClass
    public static void oneTimeTearDown() throws SQLException {
        for (User user : users) {
            userDao.remove(user);
        }
        userDao.remove(userToAdd);
        userDao.remove(userToUpdate);
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFindByUsername() throws Exception {
        User user = userDao.findByUsername(USERNAME_TO_FIND);
        assertNotNull(user);
    }

    @Test
    public void testFindByEmail() throws Exception {
        User user = userDao.findByEmail(EMAIL_TO_FIND);
        assertNotNull(user);
    }

    @Test
    public void testFindByUsernameAndPassword() throws Exception {
        User user = userDao.findByUsernameAndPassword(USERNAME_TO_FIND, PASSWORD_TO_FIND);
        assertNotNull(user);
    }

    @Test
    public void testFindByEmailAndPassword() throws Exception {
        User user = userDao.findByEmailAndPassword(EMAIL_TO_FIND, PASSWORD_TO_FIND);
        assertNotNull(user);
    }

    @Test
    public void testFind() throws Exception {
        User user = userDao.find(userToFind);
        assertNotNull(user);
    }

    @Test
    public void testAdd() throws Exception {
        userDao.add(userToAdd);
        User user = userDao.find(userToAdd);
        assertNotNull(user);
    }

    @Test
    public void testRemove() throws Exception {
        userDao.remove(userToRemove);
        User user = userDao.find(userToRemove);
        assertNull(user);
    }

    @Test
    public void testRemoveByUUID() throws Exception {
        userDao.removeByUUID(userToRemoveByUUID.getUuid());
        User user = userDao.find(userToRemoveByUUID);
        assertNull(user);
    }

    @Test
    public void testRemoveByEmail() throws Exception {
        userDao.removeByEmail(userToRemoveByEmail.getEmail());
        User user = userDao.find(userToRemoveByEmail);
        assertNull(user);
    }

    @Test
    public void testRemoveByUsername() throws Exception {
        userDao.removeByUsername(userToRemoveByUsername.getUsername());
        User user = userDao.find(userToRemoveByUsername);
        assertNull(user);
    }

    @Test
    public void testUpdate() throws Exception {
        userDao.update(userToUpdate);
        User user = userDao.findByUsername(userToBeUpdated.getUsername());
        assertNull(user);
        user = userDao.findByEmail(userToUpdate.getEmail());
        assertNotNull(user);
        assertEquals(user, userToUpdate);
    }

    @Test
    public void testGetUserList() throws Exception {
        List<User> userList = userDao.getUserList();
        assertNotNull(userList);
        assertTrue(userList.size() > 0);
    }

    @Test
    public void testIsDuplicate() throws Exception {
        List<String> list = userDao.isDuplicate(userToFind);
        assertNotNull(list);
        assertTrue(list.size() == 2);
    }
}