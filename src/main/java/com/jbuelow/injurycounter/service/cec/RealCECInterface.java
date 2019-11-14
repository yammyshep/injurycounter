package com.jbuelow.injurycounter.service.cec;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("useCEC")
public class RealCECInterface implements CECInterface {

}
