package main;
import java.io.IOException;
import hierarchyView.GeneratorBuilder;
import hierarchyView.generator.CircleViewGenerator;
import hierarchyView.generator.GeneratorBuilder;

public class Main {
	public static void main(String[] args) {
		String origin = args[0];
		String destination = args[1];
		try {
			CircleViewGenerator generator = GeneratorBuilder.getProjectGenerator(origin, destination);
			generator.generate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}