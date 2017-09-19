package rpraut.osgi.api;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

// TODO: Auto-generated Javadoc
/**
 * The Class TestServiceTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestServiceTest {

	/** The test service. */
	@Mock
	private TestService testService;
	
	/**
	 * Test do test.
	 */
	@Test
	public void testDoTest() {
		testService.doTest(anyString());
		verify(testService).doTest(anyString());
	}

}
