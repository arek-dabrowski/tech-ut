package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class UserTest {
	
	@Autowired
	ServiceManager serviceManager;

	private final String FIRST_NAME_1 = "John";
	private final String LAST_NAME_1 = "Doe";
	@SuppressWarnings("deprecation")
	private final Date YOB_1 = new Date(1995, 2, 2);
	
//	private final Boolean RESERVED_1 = true;
//	private final Double PRICE_1 = 199.99;
//	
//	private final Boolean RESERVED_2 = true;
//	private final Double PRICE_2 = 2999.99;
//
//	private final String GUN_NAME_1 = "MP5";
//	private final String GUN_DATE_1 = "16-04-2015";
//	private final Boolean SOLD_1 = false;
//	private final Double WEIGHT_1 = 2.19;
//	
//	private final String GUN_NAME_2 = "AK-47";
//	private final String GUN_DATE_2 = "01-01-1999";
//	private final Boolean SOLD_2 = false;
//	private final Double WEIGHT_2 = 3.14;
	
	@Test
	public void addUserCheck() {
		List<User> retrievedUsers = serviceManager.getAllUsers();

		for (User user : retrievedUsers) {
			if (user.getFirstName().equals(FIRST_NAME_1) && user.getLastName().equals(LAST_NAME_1) && user.getBirthDate().equals(YOB_1)) {
				serviceManager.deleteUser(user);
			}
		}
		
		User user = new User(FIRST_NAME_1, LAST_NAME_1, YOB_1);
		Long userId = serviceManager.addUser(user);
		User retrievedUser = serviceManager.findUserById(userId);
		
		assertEquals(FIRST_NAME_1, retrievedUser.getFirstName());
		assertEquals(LAST_NAME_1, retrievedUser.getLastName());		
		assertEquals(YOB_1, retrievedUser.getBirthDate());
		assertEquals(true, retrievedUser.getOfAge());
	}
	
//	@Test
//	public void deletUserCheck() {
//		List<Label> retrievedLabels = serviceManager.getAllLabels();
//
//		for (Label label : retrievedLabels) {
//			if (label.getReserved().equals(RESERVED_1) && label.getPrice().equals(PRICE_1)) {
//				serviceManager.deleteLabel(label);
//			}
//		}
//		
//		Label label1 = new Label(RESERVED_1, PRICE_1);
//		Label label2 = new Label(RESERVED_2, PRICE_2);
//		
//		Long label1Id = serviceManager.addLabel(label1);
//		Long label2Id = serviceManager.addLabel(label2);
//		
//		Label labelToDelete = serviceManager.findLabelById(label2Id);
//		
//		int beforeDeleteSize = serviceManager.getAllLabels().size();
//		serviceManager.deleteLabel(labelToDelete);
//		int afterDeleteSize = serviceManager.getAllLabels().size();
//		
//		Label lastLabel = serviceManager.getAllLabels().get(afterDeleteSize-1);
//		
//		assertEquals(beforeDeleteSize, afterDeleteSize + 1);
//		assertEquals(lastLabel.getId(), label1Id);
//		assertEquals(RESERVED_1, lastLabel.getReserved());
//		assertEquals(PRICE_1, lastLabel.getPrice());		
//	}
//	
//	
//	@Test
//	public void deleteUserAssignedToGunCheck() {
//		List<Label> retrievedLabels = serviceManager.getAllLabels();
//
//		for (Label label : retrievedLabels) {
//			if (label.getReserved().equals(RESERVED_1) && label.getPrice().equals(PRICE_1)) {
//				serviceManager.deleteLabel(label);
//			}
//		}
//		
//		Gun gun1 = new Gun(GUN_NAME_1, GUN_DATE_1, SOLD_1, WEIGHT_1);
//		Long gun1Id = serviceManager.addGun(gun1);
//		
//		Gun gun2 = new Gun(GUN_NAME_2, GUN_DATE_2, SOLD_2, WEIGHT_2);
//		Long gun2Id = serviceManager.addGun(gun2);
//		
//		Label label1 = new Label(RESERVED_1, PRICE_1);
//		Label label2 = new Label(RESERVED_2, PRICE_2);
//		
//		serviceManager.addLabelToGun(gun1, label1);
//		Long label2Id = serviceManager.addLabelToGun(gun2, label2);
//		
//		Label labelToDelete = serviceManager.findLabelById(label2Id);
//		
//		int beforeDeleteSize = serviceManager.getAllLabels().size();
//		serviceManager.deleteLabel(labelToDelete);
//		int afterDeleteSize = serviceManager.getAllLabels().size();
//		
//		Label lastLabel = serviceManager.getAllLabels().get(afterDeleteSize-1);
//		Gun retrievedGun1 = serviceManager.findGunById(gun1Id);
//		Gun retrievedGun2 = serviceManager.findGunById(gun2Id);
//		
//		assertEquals(beforeDeleteSize, afterDeleteSize + 1);
//		assertEquals(lastLabel.getId(), retrievedGun1.getLabel().getId());
//		assertEquals(null, retrievedGun2.getLabel());
//		assertEquals(gun1.getId(), retrievedGun1.getId());
//		assertEquals(gun2.getId(), retrievedGun2.getId());
//		assertEquals(RESERVED_1, lastLabel.getReserved());
//		assertEquals(PRICE_1, lastLabel.getPrice());		
//	}
	
}
