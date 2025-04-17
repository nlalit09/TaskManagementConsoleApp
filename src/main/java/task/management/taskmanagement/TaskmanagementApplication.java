package task.management.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages="task.management.taskmanagement",excludeFilters = @ComponentScan.Filter(
		type = FilterType.REGEX,
		pattern = "task\\.management\\.taskmanagement\\.CorsConfig"
))
public class TaskmanagementApplication {

	public static void main(String[] args) {
		SpringApplication con=new SpringApplication(TaskmanagementApplication.class);
		con.setLazyInitialization(true);
		ApplicationContext context=SpringApplication.run(TaskmanagementApplication.class, args);
		String[] beanNames=context.getBeanDefinitionNames();
		for(String beanName:beanNames){
			System.out.println(beanName);
		}
	}

}
