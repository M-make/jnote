package org.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.util.StringUtils;

class TestCondition extends SpringBootCondition {

	@Override
	public ConditionOutcome getMatchOutcome(ConditionContext context,
                                            AnnotatedTypeMetadata md) {
		String sourceClass = "";
		if (md instanceof ClassMetadata) {
			sourceClass = ((ClassMetadata) md).getClassName();
		}
		ConditionMessage.Builder message = ConditionMessage.forCondition("ZipkinSender",
				sourceClass);
		String property = context.getEnvironment()
				.getProperty("spring.zipkin.sender.type");
		if (StringUtils.isEmpty(property)) {
			return ConditionOutcome.match(message.because("automatic sender type"));
		}
		String senderType = "";
		if (property.equalsIgnoreCase(senderType)) {
			return ConditionOutcome.match(message.because(property + " sender type"));
		}
		return ConditionOutcome.noMatch(message.because(property + " sender type"));
	}

}