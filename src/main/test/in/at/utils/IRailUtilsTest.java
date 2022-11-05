package in.at.utils;

import in.at.exceptions.IRailException;
import in.at.response.LiveBoardResponse;
import in.at.response.StationResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class IRailUtilsTest {

    @Test
    public void stationResponseShouldBeOk() {
        StationResponse stationResponse = IRailUtil.fetchStations();
        assertNotNull(stationResponse);
    }

    @Test
    public void shouldReturnOneOrMoreStations() {
        StationResponse stationResponse = IRailUtil.fetchStations();
        assertFalse(stationResponse.getStation().isEmpty());
    }

    @Test
    public void shouldDisplayLiveBoardOfAStation() {
        String stationId = "BE.NMBS.007015400";
        LiveBoardResponse liveBoardResponse = IRailUtil.fetchLiveBoard(stationId);
        assertNotNull(liveBoardResponse);
    }

    @Test
    public void shouldFailToShowLiveBoardOfAnInvalidStation() {
        try {
            IRailUtil.fetchLiveBoard("INVALID");
            fail("No error returned.");
        } catch (IRailException e) {
            assertEquals(404, e.getErrorResponse().getError());
        }
    }
}
