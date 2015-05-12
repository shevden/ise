package com.ds.ise.entity.additional;

/**
 * Representation of options, one of which may be selected while answering the
 * {@code Question} about {@code Item}.
 * 
 * @author Denis Shevchenko
 * @version 1.0 (alpha)
 */
public enum AnswerType {
	POSITIVE, NEGATIVE, PROBABLE_POSITIVE, PROBABLE_NEGATIVE, DONT_KNOW;

	public static AnswerType getByOrdinal(int ordinal){
		for(AnswerType value: values()){
			if(value.ordinal() == ordinal){
				return value;
			}
		}

		throw new IllegalArgumentException("No value for specified ordinal number");
	}
}
