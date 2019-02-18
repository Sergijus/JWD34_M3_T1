package jwd.wafepa.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.impl.InMemoryActivityService;

public class InMemoryActivityServiceTest {
	private ActivityService activityService;
	
	@Before
	public void setUp() {
		activityService = new InMemoryActivityService();
		
		Activity swimming = new Activity("Swimming");
		Activity running = new Activity("Running");
		
		activityService.save(swimming);
		activityService.save(running);
	}
	
	@Test
	public void testFindOne() {
		Activity a = activityService.findOne(2L);
		
		Assert.assertEquals("Running", a.getName());
	}
	
	@Test
	public void testFindAll() {
		List<Activity> ret = activityService.findAll();
		
		Assert.assertEquals(2, ret.size());
	}
	
	@Test
	public void testFindByName() {
		Activity a = activityService.findByName("Running");
		
		Assert.assertEquals("Running", a.getName());
	}
	
	@Test
	public void testSaveList() {
		
		List<Activity> testList = new ArrayList<>();
		
		testList.add(new Activity("Tennis"));
		testList.add(new Activity("Volleyball"));
		testList.add(new Activity("Basketball"));
		
		List<Activity> retVal = activityService.save(testList);
		
		Assert.assertEquals(testList, retVal);
		Assert.assertEquals("Basketball", retVal.get(2).getName());
		Assert.assertEquals("5", retVal.get(2).getId());
	}
	
	@Test
	public void testRemoveListIds() {
		
		List<Long> testList = new ArrayList<>();
		
		testList.add(1L);
		testList.add(5L);
		
		activityService.remove(testList);
		
		Assert.assertEquals("Removing list of IDs", 0, activityService.findAll().size());
		
	}
}
