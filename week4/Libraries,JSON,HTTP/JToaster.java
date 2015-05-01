import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.nitido.utils.toaster.Toaster;

public class JToaster {

	public static void main(String[] args) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Toaster toasterManager = new Toaster();
		toasterManager.setToasterWidth(300);
		toasterManager.setToasterHeight(80);
		toasterManager.setStep(20);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("/home/georgi/Downloads/cooldog.jpeg"));
		} catch (IOException e) {
		}
		toasterManager.setBackgroundImage(img);
		toasterManager.showToaster("Hello World!");

	}

}
