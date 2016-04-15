package com.lottstat.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lottstat.entity.StateEnum;

public class ConverterTest {

	@Test
	public void testGetInstance() {
		assertTrue(Converter.getInstance(StateEnum.FLORIDA) instanceof FloridaConverter);
		assertTrue(Converter.getInstance(StateEnum.GEORGIA) instanceof GeorgiaConverter);
		assertTrue(Converter.getInstance(StateEnum.UNKNOWN) == null);
		
	}

}
