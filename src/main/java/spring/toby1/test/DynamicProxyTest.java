package spring.toby1.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import spring.toby1.learningtest.jdk.Hello;
import spring.toby1.learningtest.jdk.HelloTarget;
import spring.toby1.learningtest.jdk.UppercaseHandler;
import spring.toby1.learningtest.spring.pointcut.Bean;
import spring.toby1.learningtest.spring.pointcut.Target;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by yuuuunmi on 2017. 11. 14..
 */
public class DynamicProxyTest {
    @Test
    public void invokeMethod() throws Exception {
        String name = "Spring";

        // length()
        assertThat(name.length(), is(6));

        Method lengthMethod = String.class.getMethod("length");
        assertThat((Integer) lengthMethod.invoke(name), is(6));

        // charAt()
        assertThat(name.charAt(0), is('S'));

        Method charAtMethod = String.class.getMethod("charAt", int.class);
        assertThat((Character) charAtMethod.invoke(name, 0), is('S'));
    }

    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();
        assertThat(hello.sayHello("Toby"), is("Hello Toby"));
        assertThat(hello.sayHi("Toby"), is("Hi Toby"));
        assertThat(hello.sayThankU("Toby"), is("Thank You Toby"));

        Hello proxiedHello = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{Hello.class},
                new UppercaseHandler(new HelloTarget()));
        assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
        assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
        assertThat(proxiedHello.sayThankU("Toby"), is("THANK YOU TOBY"));
    }


    @Test
    public void proxyFactoryBean() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());
        pfBean.addAdvice(new UppercaseAdvice());

        Hello proxiedHello = (Hello) pfBean.getObject();
        assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
        assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
        assertThat(proxiedHello.sayThankU("Toby"), is("THANK YOU TOBY"));

    }

    @Test
    public void pointcutAdvisor() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("sayH*");

        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

        Hello proxiedHello = (Hello) pfBean.getObject();

        assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
        assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
        assertThat(proxiedHello.sayThankU("Toby"), is("Thank You Toby"));

    }

    @Test
    public void classNamePointcutAdvisor() {
        // 포인트컷 준비
        NameMatchMethodPointcut classMethodPointcut = new NameMatchMethodPointcut() {
            public ClassFilter getClassFilter() {
                return new ClassFilter() {
                    public boolean matches(Class<?> aClass) {
                        return aClass.getSimpleName().startsWith("HelloT");
                    }
                };
            }
        };
        classMethodPointcut.setMappedName("sayH*");

        checkAdviced(new HelloTarget(), classMethodPointcut, true);

        class HelloWorld extends HelloTarget {
        }
        ;
        checkAdviced(new HelloWorld(), classMethodPointcut, false);

        class HelloToby extends HelloTarget {
        }
        ;
        checkAdviced(new HelloToby(), classMethodPointcut, true);
    }

    @Test
    public void methodSignaturePointcut() throws SecurityException, NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public int " +
                "spring.toby1.learningtest.spring.pointcut.Target.minus(int,int) " +
                "throws java.lang.RuntimeException)");

        // Target.minus()
        assertThat(pointcut.getClassFilter().matches(Target.class)
                && pointcut.getMethodMatcher().matches(
                Target.class.getMethod("minus", int.class, int.class), null), is(true));

        // Target.plus()
        assertThat(pointcut.getClassFilter().matches(Target.class)
                && pointcut.getMethodMatcher().matches(
                Target.class.getMethod("plus", int.class, int.class), null), is(false));

        // Bean.method()
        assertThat(pointcut.getClassFilter().matches(Bean.class)
                && pointcut.getMethodMatcher().matches(
                Target.class.getMethod("method"), null), is(false));

    }
    @Test
    public void pointcut() throws Exception{
        targetClassPointcutMatches("execution(* *(..))",
                true, true, true, true, true, true);
        targetClassPointcutMatches("execution(* hello(..))",
                true, true, false, false, false, false);
        targetClassPointcutMatches("execution(* hello(String))",
                false, true, false, false, false, false);
        targetClassPointcutMatches("execution(* meth*(..))",
                false, false, false, false, true, true);
        targetClassPointcutMatches("execution(* *(int, int))",
                false, false, true, true, false, false);
        targetClassPointcutMatches("execution(* *())",
                true, false, false, false, true, true);
        targetClassPointcutMatches("execution(* spring.toby1.learningtest.spring.pointcut.Target.*(..))",
                true, true, true, true, true, false);
        targetClassPointcutMatches("execution(* spring.toby1.learningtest.spring.pointcut.*.*(..))",
                true, true, true, true, true, true);
        targetClassPointcutMatches("execution(* spring.toby1.learningtest.spring.pointcut..*.*(..))",
                true, true, true, true, true, true);
        targetClassPointcutMatches("execution(* spring.toby1..*.*(..))",
                true, true, true, true, true, true);
        targetClassPointcutMatches("execution(* com..*.*(..))",
                false, false, false, false, false, false);
        targetClassPointcutMatches("execution(* *..Target.*(..))",
                true, true, true, true, true, false);
        targetClassPointcutMatches("execution(* *..Tar*.*(..))",
                true, true, true, true, true, false);
        targetClassPointcutMatches("execution(* *..*get.*(..))",
                true, true, true, true, true, false);
        targetClassPointcutMatches("execution(* *..B*.*(..))",
                false, false, false, false, false, true);
        targetClassPointcutMatches("execution(* *..TargetInterface.*(..))",
                true, true, true, true, false, false);
        targetClassPointcutMatches("execution(* *(..) throws Runtime*)",
                false, false, false, true, false, true);
        targetClassPointcutMatches("execution(int *(..))",
                false, false, true, true, false, false);
        targetClassPointcutMatches("execution(void *(..))",
                true, true, false, false, true, true);
    }

    public void targetClassPointcutMatches(String expression, boolean... expected) throws Exception{
        pointcutMatches(expression, expected[0], Target.class, "hello");
        pointcutMatches(expression, expected[1], Target.class, "hello", String.class);
        pointcutMatches(expression, expected[2], Target.class, "plus", int.class, int.class);
        pointcutMatches(expression, expected[3], Target.class, "minus", int.class, int.class);
        pointcutMatches(expression, expected[4], Target.class, "method");
        pointcutMatches(expression, expected[5], Bean.class, "method");
    }

    public void pointcutMatches(String expression, Boolean expected, Class<?> clazz, String methodName, Class<?>... args) throws Exception{
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);

        assertThat(pointcut.getClassFilter().matches(clazz)
                && pointcut.getMethodMatcher().matches(
                clazz.getMethod(methodName, args), null), is(expected));

    }

    private void checkAdviced(Object target, Pointcut pointcut, boolean adviced) {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(target);
        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
        Hello proxiedHello = (Hello) pfBean.getObject();

        if (adviced) {
            assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
            assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
            assertThat(proxiedHello.sayThankU("Toby"), is("Thank You Toby"));
        } else {
            assertThat(proxiedHello.sayHello("Toby"), is("Hello Toby"));
            assertThat(proxiedHello.sayHi("Toby"), is("Hi Toby"));
            assertThat(proxiedHello.sayThankU("Toby"), is("Thank You Toby"));
        }
    }


    static class UppercaseAdvice implements MethodInterceptor {

        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            String ret = (String) methodInvocation.proceed();
            return ret.toUpperCase();
        }
    }

}
