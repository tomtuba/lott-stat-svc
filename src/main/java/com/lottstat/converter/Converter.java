package com.lottstat.converter;

import static org.apache.commons.lang3.StringUtils.remove;
import static org.apache.commons.lang3.StringUtils.substringBefore;

import com.lottstat.entity.State;
import com.lottstat.entity.StateEnum;

public abstract class Converter {
	
	public abstract State convertState(String html);
	public abstract String getURL();
	public static Converter getInstance(StateEnum stateEnum){
		switch(stateEnum){
		case ALABAMA:
			break;
		case ALASKA:
			break;
		case AMERICAN_SAMOA:
			break;
		case ARIZONA:
			break;
		case ARKANSAS:
			break;
		case CALIFORNIA:
			return new CaliforniaConverter();
		case COLORADO:
			break;
		case CONNECTICUT:
			break;
		case DELAWARE:
			return new DelawareConverter();
		case DISTRICT_OF_COLUMBIA:
			break;
		case FEDERATED_STATES_OF_MICRONESIA:
			break;
		case FLORIDA:
			return new FloridaConverter();
		case GEORGIA:
			return new GeorgiaConverter();
		case GUAM:
			break;
		case HAWAII:
			break;
		case IDAHO:
			break;
		case ILLINOIS:
			break;
		case INDIANA:
			break;
		case IOWA:
			return new IowaConverter();
		case KANSAS:
			break;
		case KENTUCKY:
			break;
		case LOUISIANA:
			return new LouisianaConverter();
		case MAINE:
			break;
		case MARSHALL_ISLANDS:
			break;
		case MARYLAND:
			break;
		case MASSACHUSETTS:
			break;
		case MICHIGAN:
			break;
		case MINNESOTA:
			break;
		case MISSISSIPPI:
			break;
		case MISSOURI:
			break;
		case MONTANA:
			break;
		case NEBRASKA:
			break;
		case NEVADA:
			break;
		case NEW_HAMPSHIRE:
			break;
		case NEW_JERSEY:
			break;
		case NEW_MEXICO:
			return new NewMexicoConverter();
		case NEW_YORK:
			break;
		case NORTHERN_MARIANA_ISLANDS:
			break;
		case NORTH_CAROLINA:
			break;
		case NORTH_DAKOTA:
			break;
		case OHIO:
			break;
		case OKLAHOMA:
			break;
		case OREGON:
			break;
		case PALAU:
			break;
		case PENNSYLVANIA:
			break;
		case PUERTO_RICO:
			break;
		case RHODE_ISLAND:
			break;
		case SOUTH_CAROLINA:
			break;
		case SOUTH_DAKOTA:
			break;
		case TENNESSEE:
			break;
		case TEXAS:
			break;
		case UNKNOWN:
			break;
		case UTAH:
			break;
		case VERMONT:
			break;
		case VIRGINIA:
			break;
		case VIRGIN_ISLANDS:
			break;
		case WASHINGTON:
			break;
		case WEST_VIRGINIA:
			break;
		case WISCONSIN:
			break;
		case WYOMING:
			break;
		default:
			break;
		
		}
		return null;
	}
	protected int convertNumber(String html){
		// $250,000.00
		String dollars = substringBefore(html, ".");
		// $250,000
		String noComma = remove(dollars, ",");
		// $250000
		String dollarSign = remove(noComma, "$");
		// 250000
		String noWords = substringBefore(dollarSign, " ");
		String noSlash = substringBefore(noWords, "/");
		String noStar = remove(noSlash, "*");
		return Integer.parseInt(noStar);
	}
	
}

