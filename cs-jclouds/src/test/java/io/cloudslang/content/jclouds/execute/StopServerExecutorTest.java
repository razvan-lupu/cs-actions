package io.cloudslang.content.jclouds.execute;

import io.cloudslang.content.jclouds.entities.constants.Outputs;
import io.cloudslang.content.jclouds.entities.inputs.CommonInputs;
import io.cloudslang.content.jclouds.entities.inputs.CustomInputs;
import io.cloudslang.content.jclouds.execute.instances.StopInstancesExecutor;
import io.cloudslang.content.jclouds.factory.ComputeFactory;
import io.cloudslang.content.jclouds.services.ComputeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by persdana on 7/6/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({StopInstancesExecutor.class, ComputeFactory.class})
public class StopServerExecutorTest {
    private StopInstancesExecutor toTest;
    private AmazonInputs inputs;

    @Mock
    private ComputeService computeServiceMock;

    @Before
    public void init() {
        mockStatic(ComputeFactory.class);

        toTest = new StopInstancesExecutor();
        inputs = AmazonInputs.getAmazonInstance();
    }

    @After
    public void tearDown() {
        toTest = null;
        inputs = null;
    }

    /**
     * Tests the execute method. Positive scenario.
     *
     * @throws Exception
     */
    @Test
    public void testExecute() throws Exception {
        when(ComputeFactory.getComputeService(any(CommonInputs.class))).thenReturn(computeServiceMock);
        doReturn("").when(computeServiceMock).stopInstances(anyString(), anyString(), anyBoolean());

        Map<String, String> result = toTest.execute(getCommonInputs(inputs), getCustomInputs(inputs));

        verify(computeServiceMock).stopInstances(inputs.getRegion(), inputs.getServerId(), false);

        assertNotNull(result);
        assertEquals("0", result.get(Outputs.RETURN_CODE));
    }

    private CommonInputs getCommonInputs(AmazonInputs inputs) throws Exception {
        return new CommonInputs.CommonInputsBuilder()
                .withProvider(inputs.getProvider())
                .withEndpoint(inputs.getEndpoint())
                .withIdentity(inputs.getIdentity())
                .withCredential(inputs.getCredential())
                .withProxyHost(inputs.getProxyHost())
                .withProxyPort(inputs.getProxyPort())
                .build();
    }

    private CustomInputs getCustomInputs(AmazonInputs inputs) {
        return new CustomInputs.CustomInputsBuilder()
                .withRegion(inputs.getRegion())
                .withInstanceId(inputs.getServerId())
                .build();
    }
}
