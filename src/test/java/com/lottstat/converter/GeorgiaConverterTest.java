package com.lottstat.converter;

import static org.junit.Assert.*;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import com.lottstat.entity.State;
import com.lottstat.entity.StateEnum;

public class GeorgiaConverterTest extends ConverterTestSupport {

	@Test
	public void testConvertState() {
		GeorgiaConverter converter = new GeorgiaConverter();
		String html = getTestHtml(StateEnum.GEORGIA.getAbbreviation());
		
		State result = converter.convertState(html);
		
		assertTrue(result != null);
		assertEquals(StateEnum.GEORGIA.getAbbreviation(),result.getAbbrev());
		assertEquals(StateEnum.GEORGIA.getAbbreviation(),result.getAbbrev());
		assertTrue(CollectionUtils.isNotEmpty(result.getGames()));
		assertEquals(3,result.getGames().size());
	}

}
