package hierarchyView.generator;

import hierarchyView.util.RGB;
import hierarchyView.visitors.metrics.ClassMetric;

public class ClassCircleViewGenerator extends CircleViewGenerator {
	private ClassMetric metric;

	public ClassCircleViewGenerator(String originFile, String targetFolder, ClassMetric metric) {
		super(originFile, targetFolder);
		this.metric = metric;
	}

	@Override
	public RGB getStrokeColor() {
		int red = 0, green = 0, blue = 0, childrenSize = children.size();

		for (IHierarchyView child : children) {
			RGB color = child.getStrokeColor();
			red += color.getRed();
			green += color.getGreen();
			blue += color.getBlue();
		}

		if(childrenSize > 0) {
			return new RGB((int) red / childrenSize, (int) green / childrenSize, (int) blue / childrenSize);
		} else {
			return new RGB(0, 0, 255);
		}
	}

	@Override
	public RGB getBackgroundColor() {
		int red = 0;
		int green = 0
		int blue = 0;
		int childrenSize = children.size();

		for (IHierarchyView child : children) {
			RGB color = child.getBackgroundColor();
			red += color.getRed();
			green += color.getGreen();
			blue += color.getBlue();
		}
		
		if(childrenSize > 0) {
			return new RGB((int) red / childrenSize, (int) green / childrenSize, (int) blue / childrenSize);
		} else {
			return new RGB(0, 255, 0);
		}
	}

	@Override
	public int getNumberOfLines() {
		int childrenSize = 0;
		for (IHierarchyView child : children) {
			childrenSize += child.getNumberOfLines();
		}
		return metric.getNumberOfLine() - childrenSize;
	}

	@Override
	public String getName() {
		return metric.getName();
	}

}
