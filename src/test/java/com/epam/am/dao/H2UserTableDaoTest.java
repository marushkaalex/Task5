package com.epam.am.dao;

import com.epam.am.entity.User;
import org.junit.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class H2UserTableDaoTest {
    public static final String USERNAME_TO_FIND = "testUsername";
    public static final String EMAIL_TO_FIND = "test@email.com";
    public static final String PASSWORD_TO_FIND = "testPassword";
    public static final String USERNAME_TO_ADD = "addedTestUsername";
    public static final String EMAIL_TO_ADD = "added@email.com";
    public static final String PASSWORD_TO_ADD = "addedTestPassword";
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
    private static UserDao userDao;
    private static List<User> users;
    private static DaoFactory daoFactory;
    private static DaoManager daoManager;

    static {

        try {
            daoFactory = new H2DaoFactory();
            daoManager = daoFactory.getDaoManager();
            userDao = daoManager.getUserDao();
        } catch (DaoException e) {
            e.printStackTrace();
        }

        userToFind = new User.Builder()
                .username(USERNAME_TO_FIND)
                .email(EMAIL_TO_FIND)
                .password(PASSWORD_TO_FIND)
                .role(User.Role.CLIENT)
                .dateOfBirth(new Date())
                .build();

        userToAdd = new User.Builder()
                .username(USERNAME_TO_ADD)
                .email(EMAIL_TO_ADD)
                .password(PASSWORD_TO_ADD)
                .role(User.Role.CLIENT)
                .dateOfBirth(new Date())
                .build();

        userToRemoveByUUID = createUser(TO_REMOVE_BY_UUID);
        userToRemove = createUser(TO_REMOVE);
        userToRemoveByEmail = createUser(TO_REMOVE_BY_EMAIL);
        userToRemoveByUsername = createUser(TO_REMOVE_BY_USERNAME);
        userToBeUpdated = createUser(TO_BE_UPDATED); //to update user there must be the same ID
        userToUpdate = createUser(TO_UPDATE);

        users = new ArrayList<>();
        users.add(userToFind);
        users.add(userToRemove);
        users.add(userToRemoveByUUID);
        users.add(userToRemoveByEmail);
        users.add(userToRemoveByUsername);
        users.add(userToBeUpdated);
    }

    private static User createUser(String name) {
        return new User.Builder()
                .username(name)
                .email(name)
                .password(name)
                .role(User.Role.CLIENT)
                .dateOfBirth(new Date())
                .build();
    }

    @BeforeClass
    public static void oneTimeSetUp() throws DaoException {

        for (User user : users) {
            userDao.add(user);
        }

        userToUpdate.setId(userToBeUpdated.getId());

        assertTrue(userDao.getUserList().size() >= 6);
    }

    @AfterClass
    public static void oneTimeTearDown() throws DaoException {
        for (User user : users) {
            userDao.remove(user);
        }
        userDao.remove(userToAdd);
        userDao.remove(userToUpdate);

        DaoUtil.close(daoManager.getConnection());
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
    public void testRemoveByID() throws Exception {
        userDao.removeByID(userToRemoveByUUID.getId());
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