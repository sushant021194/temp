package Codebase.FunctionLibrary;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
public class ImageComparison {
	LinkedList<Rect> rectangles = null;
	LinkedList<Rect> innerRectangles  = null;
	BufferedImage gbiBaseImage;	
	Graphics2D g2d;
	BufferedOutputStream gbo;
	private static int IMG_WIDTH;
	private static int IMG_HEIGHT;
	private static String DRIVE;
	private static int flag=0;
	
//	public static void main(String[] args) {
//		ImageComparison c = new ImageComparison();
//		//c.compareImage(args[0],args[1],args[2]);
//		c.compareImage("../ImageComparsionDemo/src/Images/Source/Source3.png", "../ImageComparsionDemo/src/Images/Target/Target3.png", "../ImageComparsionDemo/src/Images/Result");
//	}
	
	/*This Function is Used to resize the differnt sized images*/
	private BufferedImage resizeImage(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		return resizedImage;
	}

	/* This function is used to calculate the difference between two images and store them in LinkedList*/
	private LinkedList<Rect> differenceImage(int[][] baseFrame, int[][] screenShot, int xOffset, int yOffset, int width, int height) {
		int xRover = 0;
		int yRover = 0;
		int index = 0;
		int limit = 0;
		int rover = 0;

		boolean isRectChanged = false;
		boolean shouldSkip = false;

		rectangles = new LinkedList<Rect>();
		Rect rect = null;

		/* xRover - Rovers over the height of 2D Array */
		/* yRover - Rovers over the width of 2D Array */
		int verticalLimit = xOffset + height;
		int horizontalLimit = yOffset + width;

		for(xRover = xOffset; xRover < verticalLimit; xRover += 1) {
			for(yRover = yOffset; yRover < horizontalLimit; yRover += 1) {

				if(baseFrame[xRover][yRover] != screenShot[xRover][yRover]) {
					/* Skip over the already processed Rectangles */
					for(Rect itrRect : rectangles) {
						if(( (xRover < itrRect.x + itrRect.h) && (xRover >= itrRect.x) ) && ( (yRover < itrRect.y + itrRect.w) && (yRover >= itrRect.y) )) {
							shouldSkip = true;
							yRover = itrRect.y + itrRect.w - 1;
							break;
						} 
					}
					if(shouldSkip) {
						shouldSkip = false;
						/* Need to come out of the if condition as below that is why "continue" has been provided */
						continue;
					}

					rect = new Rect();
					rect.x = ((xRover - 1) < xOffset) ? xOffset : (xRover - 1);
					rect.y = ((yRover - 1) < yOffset) ? yOffset : (yRover - 1);
					rect.w = 2;
					rect.h = 2;

					/* Boolean variable used to re-scan the currently found rectangle
      				for any change due to previous scanning of boundaries */
					isRectChanged = true;

					while(isRectChanged) {
						isRectChanged = false;
						index = 0;

						/*     I      */
						/* Scanning of left-side boundary of rectangle */
						index = rect.x;
						limit = rect.x + rect.h;
						while(index < limit && rect.y != yOffset) {
							if(baseFrame[index][rect.y] != screenShot[index][rect.y]) {        
								isRectChanged = true;
								rect.y = rect.y - 1;
								rect.w = rect.w + 1;
								index = rect.x;
								continue;
							}
							index = index + 1;;
						} 

						/*     II      */
						/* Scanning of bottom boundary of rectangle */
						index = rect.y;
						limit = rect.y + rect.w;
						while( (index < limit) && (rect.x + rect.h != verticalLimit) ) {
							rover = rect.x + rect.h - 1;
							if(baseFrame[rover][index] != screenShot[rover][index]) {
								isRectChanged = true;
								rect.h = rect.h + 1;        
								index = rect.y;
								continue;
							}
							index = index + 1;
						} 

						/*     III      */
						/* Scanning of right-side boundary of rectangle */
						index = rect.x;
						limit = rect.x + rect.h;
						while( (index < limit) && (rect.y + rect.w != horizontalLimit) ) {
							rover = rect.y + rect.w - 1;
							if(baseFrame[index][rover] != screenShot[index][rover]) {
								isRectChanged = true;
								rect.w = rect.w + 1;
								index = rect.x;
								continue;
							}
							index = index + 1;
						}
					}

					/* Remove those rectangles that come inside "rect" rectangle.*/
					int idx = 0;
					while(idx < rectangles.size()) {
						Rect r = rectangles.get(idx);
						if( ( (rect.x <= r.x) && (rect.x + rect.h >= r.x + r.h) ) && ( (rect.y <= r.y) && (rect.y + rect.w >= r.y + r.w) ) ) {
							rectangles.remove(r);
						} else {
							idx += 1;
						} 
					}
					/* Giving a head start to the yRover when a rectangle is found */
					rectangles.addFirst(rect);
					yRover = rect.y + rect.w - 1;
				}
			}
		}
		return rectangles;
	}

	/*This function creates the copy of two images in same format and plot the differences between the two */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String compareImage(String source, String target, String drive) {
		try {
			flag++;
			DRIVE = drive;
			File file1 = new File(source);
			File file2 = new File(target);
			System.out.println("Path Created"+DRIVE+"\\imagetemp"+flag);
			new File(DRIVE+"\\imagetemp"+flag).mkdir();
			System.out.println();
			BufferedImage originalImage1 = ImageIO.read(file1);
			BufferedImage originalImage2 = ImageIO.read(file2);
			if((originalImage1.getHeight()==originalImage2.getHeight()) && (originalImage1.getWidth()== originalImage2.getWidth())) {
				IMG_WIDTH = originalImage1.getWidth();
				IMG_HEIGHT = originalImage1.getHeight();
			}else{
				IMG_WIDTH = originalImage2.getWidth();
				IMG_HEIGHT = originalImage2.getHeight();
			}

			int type1 = originalImage1.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage1.getType();
			int type2 = originalImage2.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage2.getType();

			BufferedImage resizeImageJpg1 = resizeImage(originalImage1, type1);
			BufferedImage resizeImageJpg2 = resizeImage(originalImage2, type2);

			/* create a blank, RGB, same width and height, and a white background */
			BufferedImage newBufferedImage1 = new BufferedImage(resizeImageJpg1.getWidth(), resizeImageJpg1.getHeight(), BufferedImage.TYPE_INT_RGB);
			newBufferedImage1.createGraphics().drawImage(resizeImageJpg1, 0, 0, Color.WHITE, null);

			BufferedImage newBufferedImage2 = new BufferedImage(resizeImageJpg2.getWidth(), resizeImageJpg2.getHeight(), BufferedImage.TYPE_INT_RGB);
			newBufferedImage2.createGraphics().drawImage(resizeImageJpg2, 0, 0, Color.WHITE, null);

			ImageIO.write(newBufferedImage1, "png", new File(DRIVE+"\\imagetemp"+flag+"\\Source_jpg.PNG"));
			ImageIO.write(newBufferedImage2, "png", new File(DRIVE+"\\imagetemp"+flag+"\\Target_jpg.PNG"));

			/* Buffering the Base image and Screen Shot Image for further processing */
			BufferedImage actualImg = ImageIO.read(new File(DRIVE+"\\imagetemp"+flag+"\\Source_jpg.PNG"));
			BufferedImage expectedImg   = ImageIO.read(new File(DRIVE+"\\imagetemp"+flag+"\\Target_jpg.PNG"));

			int xOffset = 0;
			int yOffset = 0;
			int length = actualImg.getWidth() * actualImg.getHeight();

			/* Creating 2 Two Dimensional Arrays for Image Processing */
			int[][] baseFrame = new int[IMG_HEIGHT][IMG_WIDTH];
			int[][] screenShot = new int[IMG_HEIGHT][IMG_WIDTH];

			/*Creating 2 Single Dimensional Arrays to retrieve the Pixel Values */
			int[] baseImgPix   = new int[length];
			int[] screenShotImgPix  = new int[length];

			/* Reading the Pixels from the Buffered Image */
			actualImg.getRGB(0, 0, actualImg.getWidth(), actualImg.getHeight(), baseImgPix, 0, actualImg.getWidth());
			expectedImg.getRGB(0, 0, expectedImg.getWidth(), expectedImg.getHeight(), screenShotImgPix, 0, expectedImg.getWidth());

			/* Transporting the Single Dimensional Arrays to Two Dimensional Array */
			for(int row = 0; row < IMG_HEIGHT; row++) {
				System.arraycopy(baseImgPix, (row * IMG_WIDTH), baseFrame[row], 0, IMG_WIDTH);
				System.arraycopy(screenShotImgPix, (row * IMG_WIDTH), screenShot[row], 0, IMG_WIDTH);
			}

			/* Finding Differences between the Base Image and ScreenShot Image */
			rectangles = differenceImage(baseFrame, screenShot, xOffset, yOffset, IMG_WIDTH, IMG_HEIGHT);

			if(!rectangles.isEmpty()){
				/* Displaying the rectangles found */
				innerRectangles = new LinkedList();
				innerRectangles = rectangles;
				for (int k=0;k<2;k++){
				for(Rect innerRect:innerRectangles) {
					/* Creating Inner Bounding Box */
					for(int i = innerRect.y; i < innerRect.y + innerRect.w; i++) {    
						screenShotImgPix[ ( innerRect.x               * IMG_WIDTH) + i ] = 0xFF0000; 
						screenShotImgPix[ ((innerRect.x + innerRect.h - 1) * IMG_WIDTH) + i ] = 0xFF0000;
					}
					for(int j = innerRect.x; j < innerRect.x + innerRect.h; j++) {
						screenShotImgPix[ (j * IMG_WIDTH) + innerRect.y                ] = 0xFF0000;
						screenShotImgPix[ (j * IMG_WIDTH) + (innerRect.y + innerRect.w - 1) ] = 0xFF0000;
					}
				}
				}
				for (int k=0;k<2;k++){
				try{
				for(Rect outerRect : rectangles) {
					outerRect.x = outerRect.x-1;
					outerRect.y = outerRect.y-1;
					outerRect.w = outerRect.w+2;
					outerRect.h = outerRect.h+2;

					/* Creating Outer Bounding Box */
					for(int i = outerRect.y; i < outerRect.y + outerRect.w; i++) {    
						screenShotImgPix[ ( outerRect.x               * IMG_WIDTH) + i ] = 0xFF0000;
						screenShotImgPix[ ((outerRect.x + outerRect.h - 1) * IMG_WIDTH) + i ] = 0xFF0000;
					}
					for(int j = outerRect.x; j < outerRect.x + outerRect.h; j++) {
						screenShotImgPix[ (j * IMG_WIDTH) + outerRect.y                 ] = 0xFF0000;
						screenShotImgPix[ (j * IMG_WIDTH) + (outerRect.y + outerRect.w - 1) ] = 0xFF0000;
					}
				}
				}catch(Exception e){
					e.printStackTrace();
					System.out.println();
				}
				}
				/* Creating the Resultant Image */
				expectedImg.setRGB(0, 0, IMG_WIDTH, IMG_HEIGHT, screenShotImgPix, 0, IMG_WIDTH);
				ImageIO.write(expectedImg, "PNG", new File(DRIVE+"//imagetemp"+flag+"//Result.png"));

				System.out.println( "Images Compared Successfully Difference Found!!!");
				return "Fail,"+DRIVE+"//imagetemp"+flag+"//Result.png";
			}else
			{
				System.out.println("Images Compared Successfully No Difference Found!");
				return "PASS,abc";				
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println();
			return "error,abc";
			//JOptionPane.showMessageDialog (null, "An Exception Occurred while comparing images!!!"+"\n Exception: "+e.getMessage(), "Comparison", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
