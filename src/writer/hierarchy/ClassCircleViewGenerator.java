package writer.hierarchy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import reader.ToBytes;
import util.RGB;
import writer.hierarchy.metrics.ClassMetric;
import writer.hierarchy.visitors.ClassVisitor;

public class ClassCircleViewGenerator extends CircleViewGenerator implements IHierarchyView {
	private ClassMetric metric;

	public ClassCircleViewGenerator(ToBytes convertor, String targetFolder) {
		super(convertor, targetFolder);
		try {
			FileInputStream in = new FileInputStream(convertor.getPath());
			CompilationUnit cu = JavaParser.parse(in);
			ClassVisitor cv = new ClassVisitor();
			cv.visit(cu, null);
			metric = cv.getMetric();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RGB getNameColor() {
		MessageDigest digest;
		int red = 0, green = 0, blue = 0;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(metric.getName().getBytes(StandardCharsets.UTF_8));
			red = hash[0];
			green = hash[15];
			blue = hash[31];
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return new RGB(red, green, blue);
	}

	@Override
	public RGB getCodeColor() {
		return new RGB(255, 255, 255);
	}

	@Override
	public int getNumberOfLines() {
		return metric.getNumberOfLines();
	}

}