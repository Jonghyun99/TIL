package hello.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.HashMap;
import java.util.Map;

@ServletComponentScan //서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	private final static Logger log = LoggerFactory.getLogger(ServletApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
//		String str = "test";
//		Map map = new HashMap();
//		map.put("test","11");
//		map.put("test2",22);
//		Boolean bool = true;
//
		long beforeTime = System.currentTimeMillis();
		for(int i=0; i<=100; i++){
			String str = "김";
			log.info("테스트 = {}",str);
		}
		long afterTime = System.currentTimeMillis();
		long secDiffTime = (afterTime - beforeTime);

		System.out.println("소요 시간(m):" + secDiffTime);

//		try{
//			int a =2/0;
//		} catch(Exception e){
//			log.info("error={}",e.toString());
//		}

	}

}
