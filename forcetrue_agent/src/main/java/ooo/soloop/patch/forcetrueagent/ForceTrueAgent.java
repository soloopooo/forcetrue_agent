package ooo.soloop.patch.forcetrueagent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import java.lang.instrument.Instrumentation;

public class ForceTrueAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("[ForceTrueAgent] Loaded. Injecting...");
        new AgentBuilder.Default()
            .type(ElementMatchers.named("org.jackhuang.hmcl.util.RegionUtils"))
            .transform((builder, typeDescription, classLoader, module, protectionDomain) -> 
                builder
                    
                    .method(ElementMatchers.named("checkChinaMainlandNetwork")
                            .and(ElementMatchers.takesNoArguments())) 
                    
                    .intercept(FixedValue.value(false))
            )
            .installOn(inst);
            
        System.out.println("[ForceTrueAgent] Injection complete.");
    }
}