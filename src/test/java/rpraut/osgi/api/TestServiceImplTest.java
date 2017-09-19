package rpraut.osgi.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.sql.DataSource;

import static org.mockito.Mockito.*;

import java.sql.SQLException;

/**
 * The type Test service impl test.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestServiceImplTest {

    /**
     * The Test service.
     */
    @InjectMocks
    private TestServiceImpl testService;

    /**
     * The Data source.
     */
    @Mock
    private DataSource dataSource;

    /**
     * Do test.
     * @throws SQLException 
     *
     * @throws Exception the exception
     */
    @Test(expected = SQLException.class)
    public void doTest() throws SQLException  {
        TestServiceImpl spyTestService = spy(testService);
//        spyTestService.doTest(anyString());
//        verify(spyTestService).doTest(anyString());
//        verify(dataSource, atLeastOnce()).getConnection();

        doThrow(SQLException.class).when(spyTestService).doTest(anyString());
        spyTestService.doTest(anyString());
    }

}