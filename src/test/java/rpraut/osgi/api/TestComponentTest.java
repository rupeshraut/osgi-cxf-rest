package rpraut.osgi.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * The Class TestComponentTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestComponentTest {

	/** The test component. */
	@InjectMocks
	private TestComponent testComponent;

	/** The test service. */
	@Mock
	private TestService testService;

	/**
	 * Test get test service.
	 */
	@Test
	public final void testGetTestService() {
		final TestService expected = testComponent.getTestService();
		assertNotNull(expected);
		assertEquals(expected, testService);
	}

	/**
	 * Test bind test service.
	 */
	@Test
	public final void testBindTestService() {
		testComponent.bindTestService(testService);
		assertNotNull(testComponent.getTestService());
	}

	/**
	 * Test unbind test service.
	 */
	@Test
	public final void testUnbindTestService() {
		testComponent.unbindTestService(testService);
		assertNull(testComponent.getTestService());
	}

	/**
	 * Test activate.
	 */
	@Test
	public final void testActivate() {
		// setup
		TestComponent spy = spy(testComponent);
		@SuppressWarnings("unchecked")
		ArgumentCaptor<Map<String, Object>> argumentCaptor = ArgumentCaptor.forClass(Map.class);
		Map<String, Object> map = new HashMap<>();
		map.put("key", Integer.valueOf(1));

		// call
		spy.activate(map);

		// verify
		verify(spy, times(1)).activate(argumentCaptor.capture());
		assertNotNull(argumentCaptor.getValue());
		assertEquals(1, argumentCaptor.getValue().get("key"));

		verify(spy, times(1)).modified(argumentCaptor.capture());
		assertEquals(1, argumentCaptor.getValue().get("key"));
		assertNotNull(argumentCaptor.getValue());
	}

	/**
	 * Test modified.
	 */
	@Test
	public final void testModified() {
		// setup
		TestComponent spy = spy(testComponent);
		@SuppressWarnings("unchecked")
		ArgumentCaptor<Map<String, Object>> argumentCaptor = ArgumentCaptor.forClass(Map.class);
		Map<String, Object> map = new HashMap<>();
		map.put("key", Integer.valueOf(1));

		// call
		spy.modified(map);

		// verify
		verify(spy, times(1)).modified(argumentCaptor.capture());
		assertNotNull(argumentCaptor.getValue());
		assertEquals(1, argumentCaptor.getValue().get("key"));
	}

	/**
	 * Test deactivate.
	 */
	@Test
	public final void testDeactivate() {
		// setup
		TestComponent spy = spy(testComponent);
		@SuppressWarnings("unchecked")
		ArgumentCaptor<Map<String, Object>> argumentCaptor = ArgumentCaptor.forClass(Map.class);
		Map<String, Object> map = new HashMap<>();
		map.put("key", Integer.valueOf(1));

		// call
		spy.deactivate(map);

		// verify
		verify(spy, times(1)).deactivate(argumentCaptor.capture());
		assertNotNull(argumentCaptor.getValue());
		assertEquals(1, argumentCaptor.getValue().get("key"));
	}

}
