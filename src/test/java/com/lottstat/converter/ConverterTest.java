package com.lottstat.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lottstat.entity.StateEnum;

public class ConverterTest {

	@Test
	public void testGetInstance() {
		assertTrue(Converter.getInstance(StateEnum.FLORIDA) instanceof FloridaConverter);
		assertTrue(Converter.getInstance(StateEnum.GEORGIA) instanceof GeorgiaConverter);
		assertTrue(Converter.getInstance(StateEnum.CALIFORNIA) instanceof CaliforniaConverter);
		assertTrue(Converter.getInstance(StateEnum.DELAWARE) instanceof DelawareConverter);
		assertTrue(Converter.getInstance(StateEnum.LOUISIANA) instanceof LouisianaConverter);
		//assertTrue(Converter.getInstance(StateEnum.NEW_HAMPSHIRE) instanceof NewHampshireConverter);
		assertTrue(Converter.getInstance(StateEnum.NEW_MEXICO) instanceof NewMexicoConverter);
		assertTrue(Converter.getInstance(StateEnum.IOWA) instanceof IowaConverter);
		assertTrue(Converter.getInstance(StateEnum.UNKNOWN) == null);
		
	}

}
