package Workflow.LBR.Action;

import com.kuka.roboticsAPI.geometricModel.Frame;

//math stuff with points, dont care about orientations, always take first frame orientation
public class FancyFrame extends Frame {

	public FancyFrame(double x, double y, double z, double a, double b, double c) {
		super(x, y, z, a, b, c);
	}

	public FancyFrame fromFrame(Frame x) {
		return new FancyFrame(x.getX(), x.getY(), x.getZ(), x.getAlphaRad(),
				x.getBetaRad(), x.getGammaRad());
	}

	public static FancyFrame Create(Frame f) {
		FancyFrame result = new FancyFrame(f.getX(), f.getY(), f.getZ(),
				f.getAlphaRad(), f.getBetaRad(), f.getGammaRad());

		result.setParent(f.getParent());

		return result;
	}

	public FancyFrame minus(FancyFrame frame) {
		FancyFrame result = new FancyFrame(getX() - frame.getX(), getY()
				- frame.getY(), getZ() - frame.getZ(), getAlphaRad(),
				getBetaRad(), getGammaRad());
		result.setParent(getParent());

		return result;
	}

	public FancyFrame add(FancyFrame frame) {
		FancyFrame result = new FancyFrame(getX() + frame.getX(), getY()
				+ frame.getY(), getZ() + frame.getZ(), getAlphaRad(),
				getBetaRad(), getGammaRad());
		result.setParent(getParent());

		return result;
	}

	public FancyFrame times(double scalar) {
		FancyFrame result = new FancyFrame(scalar * getX(), scalar * getY(),
				getZ() * scalar, getAlphaRad(), getBetaRad(), getGammaRad());
		result.setParent(getParent());

		return result;
	}

	public FancyFrame copyWithRedundancyWrap() {
		return FancyFrame.Create(this.copyWithRedundancy());
	}

}
