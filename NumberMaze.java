import java.awt.event.KeyListener;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.Color;
import enigma.core.Enigma;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import enigma.console.TextAttributes;
import java.awt.Color;
import enigma.core.Enigma;

public class NumberMaze {
	// The top class of the game
	private boolean gameOver;
	private static int score;
	private static int playerNumber;
	private int px; 
	private int py;
	private Queue inputs;
	private static int time;
	private static enigma.console.Console cn;
	private KeyListener klis;
	private int keypr;
	private int rkey;
	private int[][] walls;
	private Number[] numbers;
	private int numberCounter;
	private static int counterMakingTwo;
	private boolean difficulty;
	private Backpacks backpacks;
	public NumberMaze() {
		this.gameOver=false;
		time=0;
		numberCounter=0;
		this.px=5;
		this.py=5;
		playerNumber = 5;
		cn = Enigma.getConsole("Number Maze", 120, 30, 15);
		//this.cn = Enigma.getConsole("Number Maze");
		this.walls = new int[23][55];
		this.numbers = new Number[500]; 							/////////
		this.inputs = new Queue(10000);								/////////
		counterMakingTwo = -1;
		backpacks = new Backpacks();
		startGame();
	}
	public void startGame() {
		int cx = 10;
		int cy = 15;
		klis=new KeyListener() {
	         public void keyTyped(KeyEvent e) {}
	         public void keyPressed(KeyEvent e) {
	            if(keypr==0) {
	               keypr=1;
	               rkey=e.getKeyCode();
	            }
	         }
	         public void keyReleased(KeyEvent e) {}
	      };
	      System.out.println("         _            _                  _   _         _               _            _      \r\n"
	      		+ "        /\\ \\     _   /\\_\\               /\\_\\/\\_\\ _    / /\\            /\\ \\         /\\ \\    \r\n"
	      		+ "       /  \\ \\   /\\_\\/ / /         _    / / / / //\\_\\ / /  \\          /  \\ \\       /  \\ \\   \r\n"
	      		+ "      / /\\ \\ \\_/ / /\\ \\ \\__      /\\_\\ /\\ \\/ \\ \\/ / // / /\\ \\        / /\\ \\ \\     / /\\ \\ \\  \r\n"
	      		+ "     / / /\\ \\___/ /  \\ \\___\\    / / //  \\____\\__/ // / /\\ \\ \\      / / /\\ \\_\\   / / /\\ \\_\\ \r\n"
	      		+ "    / / /  \\/____/    \\__  /   / / // /\\/________// / /\\ \\_\\ \\    / /_/_ \\/_/  / / /_/ / / \r\n"
	      		+ "   / / /    / / /     / / /   / / // / /\\/_// / // / /\\ \\ \\___\\  / /____/\\    / / /__\\/ /  \r\n"
	      		+ "  / / /    / / /     / / /   / / // / /    / / // / /  \\ \\ \\__/ / /\\____\\/   / / /_____/   \r\n"
	      		+ " / / /    / / /     / / /___/ / // / /    / / // / /____\\_\\ \\  / / /______  / / /\\ \\ \\     \r\n"
	      		+ "/ / /    / / /     / / /____\\/ / \\/_/    / / // / /__________\\/ / /_______\\/ / /  \\ \\ \\    \r\n"
	      		+ "\\/_/     \\/_/      \\/_________/          \\/_/ \\/_____________/\\/__________/\\/_/    \\_\\/ ");
	      System.out.println("								        _   _         _             _                 _      \r\n"
	      		+ "								       /\\_\\/\\_\\ _    / /\\         /\\ \\               /\\ \\    \r\n"
	      		+ "								      / / / / //\\_\\ / /  \\       /  \\ \\             /  \\ \\   \r\n"
	      		+ "								     /\\ \\/ \\ \\/ / // / /\\ \\   __/ /\\ \\ \\           / /\\ \\ \\  \r\n"
	      		+ "								    /  \\____\\__/ // / /\\ \\ \\ /___/ /\\ \\ \\         / / /\\ \\_\\ \r\n"
	      		+ "								   / /\\/________// / /  \\ \\ \\\\___\\/ / / /        / /_/_ \\/_/ \r\n"
	      		+ "								  / / /\\/_// / // / /___/ /\\ \\     / / /        / /____/\\    \r\n"
	      		+ "								 / / /    / / // / /_____/ /\\ \\   / / /    _   / /\\____\\/    \r\n"
	      		+ "								/ / /    / / // /_________/\\ \\ \\  \\ \\ \\__/\\_\\ / / /______    \r\n"
	      		+ "								\\/_/    / / // / /_       __\\ \\_\\  \\ \\___\\/ // / /_______\\   \r\n"
	      		+ "								        \\/_/ \\_\\___\\     /____/_/   \\/___/_/ \\/__________/   \r\n"
	      		+ "					                                                            ");
	      cn.getTextWindow().setCursorPosition(cx-7, cy-1);
	      cn.getTextWindow().output("PLEASE CHOOSE DIFFICULTY");
	      cn.getTextWindow().addKeyListener(klis);
	      cn.getTextWindow().setCursorPosition(cx, cy);
	      TextAttributes attrs=new TextAttributes(Color.GREEN);
	      cn.getTextWindow().output("NORMAL\n", attrs);  
	      cn.getTextWindow().setCursorPosition(cx, cy+1);
	      attrs=new TextAttributes(Color.RED);
	      cn.getTextWindow().output("NIGHTMARE", attrs);
	      while (true)
	      {
		      if(keypr==1) {    // if keyboard button pressed
		    	  if (rkey==KeyEvent.VK_ENTER) break;  
		    	  if(rkey==KeyEvent.VK_UP) {
		    		  playSound("game.wav",false);
	            		if(cy == 6) {
	            			cy--;
	            			
		            		attrs=new TextAttributes(Color.GREEN);
		            		cn.getTextWindow().setCursorPosition(10, 15);
	            		    cn.getTextWindow().output("NORMAL", attrs);
	            		    attrs=new TextAttributes(Color.RED);
	            		    cn.getTextWindow().setCursorPosition(10,16);
	            		    cn.getTextWindow().output("NIGHTMARE", attrs);
	            		}
	            		else {
	            			cy++;
	            			
	            			attrs=new TextAttributes(Color.RED);
	            			cn.getTextWindow().setCursorPosition(10, 15);
	            		    cn.getTextWindow().output("NORMAL", attrs);
	            		    attrs=new TextAttributes(Color.GREEN);
	            		    cn.getTextWindow().setCursorPosition(10, 16);
	            		    cn.getTextWindow().output("NIGHTMARE", attrs);
						}     
	            	}
		    	  if(rkey==KeyEvent.VK_DOWN) {
		    		  playSound("game.wav", false);
	            		if(cy == 5) {
	            			cy++;
		            		attrs=new TextAttributes(Color.RED);
		            		cn.getTextWindow().setCursorPosition(10, 15);
	            		    cn.getTextWindow().output("NORMAL", attrs);
	            		    attrs=new TextAttributes(Color.GREEN);
	            		    cn.getTextWindow().setCursorPosition(10, 16);
	            		    cn.getTextWindow().output("NIGHTMARE", attrs);
	            		}
	            		else {
	            			cy--;
	            			attrs=new TextAttributes(Color.GREEN);
	            			cn.getTextWindow().setCursorPosition(10, 15);
	            		    cn.getTextWindow().output("NORMAL", attrs);
	            		    attrs=new TextAttributes(Color.RED);
	            		    cn.getTextWindow().setCursorPosition(10, 16);
	            		    cn.getTextWindow().output("NIGHTMARE", attrs);
						}
	            	}  
		      }
	            keypr=0;    // last action     
	            try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
         }
	      
	    if (cy==15) difficulty=true;
	    else difficulty=false;
	    for (int i = 0; i < 23; i++) {
	    	for (int j = 0; j < 100; j++) {
	    		cn.getTextWindow().output(j,i,' ');
			}
		}
		int count = 0;
		cn.getTextWindow().setCursorPosition(0, 0);
		cn.getTextWindow().output(64,20,'0');
		File path = new File("Maze.txt");
		try {
			Scanner reading = new Scanner(path);
			while (reading.hasNextLine()) {
				String line = reading.nextLine();
				cn.getTextWindow().output(line + '\n');
				settingGameArea(line, count);
				count++;
			}
			reading.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playSound("sound.wav", true);
		// At this point, walls are initialized
		settingRandomNumber();
		// Enigma
		showNumbers();
		setPathsRed();
		
		// generate next number
		createInputs();

		enigma();
		// Starting by reading the input file
	}

	public void playSound(String url, boolean loop) {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        if(loop == true) {
	        	clip.loop(counterMakingTwo);
	        }
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public void enigma() {
			klis=new KeyListener() {
	         public void keyTyped(KeyEvent e) {}
	         public void keyPressed(KeyEvent e) {
	            if(keypr==0) {
	               keypr=1;
	               rkey=e.getKeyCode();
	            }
	         }
	         public void keyReleased(KeyEvent e) {}
	      };
	      cn.getTextWindow().addKeyListener(klis);
	      cn.getTextWindow().setCursorPosition(px, py);
	      TextAttributes attrs=new TextAttributes(Color.BLUE);
	      cn.getTextWindow().output(String.valueOf(playerNumber),attrs);
	      attrs=new TextAttributes(Color.WHITE);
	      
	      while(!(gameOver)) {
		         if(keypr==1) {    // if keyboard button pressed
		            if(rkey==KeyEvent.VK_LEFT) px--;
		            if(rkey==KeyEvent.VK_RIGHT) px++;
		            if(rkey==KeyEvent.VK_UP) py--;
		            if(rkey==KeyEvent.VK_DOWN) py++;
		            if(rkey==KeyEvent.VK_Q)backpacks.packQOperation();;
		            if(rkey==KeyEvent.VK_W)backpacks.packWOperation();;
		            char rckey=(char)rkey;
		            //        left          right          up            down
		            if(rckey=='%' || rckey=='\'' || rckey=='&' || rckey=='(') { 
		            	int x = px;
		            	int y = py;
		            	if(rckey=='%') {
		            		x = px + 1;
		            	}
		            	else if(rckey=='\'') {
		            		x = px - 1;
		            	}
		            	else if(rckey=='&') {
		            		y = py + 1;
		            	}
		            	else if(rckey=='(') {
		            		y = py - 1;
		            	}
		            	if (walls[py][px]!=1) {
			            	cn.getTextWindow().setCursorPosition(x, y);
			            	cn.getTextWindow().output(" ");
			            	//cn.getTextWindow().output(x,y,' ');
			            	gameOver=checkCollision(px,py);
			            	setPathsRed();
			            	cn.getTextWindow().setCursorPosition(px, py);
						}else {
							px=x;
							py=y;
						}
		            }
		            keypr=0;    // last action  
		         }
		         try {
		        	standardTimeOperations();
		        	//setPathsRed();
					gameOver=checkCollision(px,py);
					if (gameOver) break;
					cn.getTextWindow().setCursorPosition(px, py);
					attrs=new TextAttributes(Color.BLUE);
					cn.getTextWindow().output(String.valueOf(playerNumber),attrs);
					gameOver=doRedMovement();
	            	Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      }
	   }

	public void standardTimeOperations() {
    	showTime();
		time++;
		if (time == counterMakingTwo + 4 && playerNumber == 1) {
			playerNumber++;
		}
		if(time%5 == 0) {
			nextNumber();
		}
		movements();
	}

	public boolean doRedMovement() {
		for (int i = 0; i < numberCounter; i++) {
			if (numbers[i]!=null && numbers[i].getColor()=='R' && numbers[i].getPath().isEmpty()==false) {
				Coordinate positionWanted=(Coordinate) numbers[i].getPath().peek();
				if (isValidPosition(positionWanted.getX(), positionWanted.getY())) {
					cn.getTextWindow().output(numbers[i].getPosition().getX(), numbers[i].getPosition().getY(),' ');
					numbers[i].changePosition();
					TextAttributes attrs = arrangeColor(i);
					cn.getTextWindow().output(numbers[i].getPosition().getX(), numbers[i].getPosition().getY(),String.valueOf(numbers[i].getValue()).charAt(0), attrs);
					if (checkCollision(px, py)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public int distance(int x1,int y1,int x,int y) {
		 int dx = Math.abs(x- x1);
		 int dy = Math.abs(y- y1);
		 return dy+dx;
	}
	public void removeDots(Number number) {
		int length=number.getPath().size();
		for (int i = 0; i < length; i++) {
			Coordinate cord=(Coordinate)number.getPath().pop();
			cn.getTextWindow().output(cord.getX(), cord.getY(), ' ');
		}
	}
	
	public void findPath(int xNumber, int yNumber, Number numberCoordinate) {
		int count=0;
		while (true) {
			int distance=distance(px, py, xNumber, yNumber);
			Coordinate movement=generateBestOption(xNumber, yNumber, numberCoordinate);
			if (movement==null) {
				return;
			}
			boolean succed=numberCoordinate.addPath(movement);
			if (!succed) return;
			
			if (count%10==0) checkPath(numberCoordinate);
			
			Coordinate lastCoordinate=(Coordinate) numberCoordinate.getPath().peek();
			xNumber = lastCoordinate.getX();
			yNumber = lastCoordinate.getY();
			if (distance==1) {
				return;
			}
			count++;
		}		
	}
	
	private void basicFindPath(int xNumber, int yNumber, Number numberCoordinate) {
		Stack movedPositions = new Stack(1000);
		Stack cancelledPositions = new Stack(1000);
		while(true) {
			boolean isMove = false;
			if (isValidPosition(xNumber+1,yNumber) && isValidCoordinateForPath(movedPositions ,xNumber+1 ,yNumber) 
					&& isValidCoordinateForPath(cancelledPositions ,xNumber+1 ,yNumber)){
				movedPositions.push(new Coordinate(xNumber+1,yNumber));
				isMove = true;
			}
			if(isValidPosition(xNumber, yNumber-1) && isValidCoordinateForPath(movedPositions ,xNumber ,yNumber-1) 
					&& isValidCoordinateForPath(cancelledPositions ,xNumber ,yNumber-1)) {
				movedPositions.push(new Coordinate(xNumber, yNumber-1));
				isMove = true;
			}
			if(isValidPosition(xNumber-1, yNumber) && isValidCoordinateForPath(movedPositions ,xNumber-1 ,yNumber) 
					&& isValidCoordinateForPath(cancelledPositions ,xNumber-1 ,yNumber)) {
				movedPositions.push(new Coordinate(xNumber-1, yNumber));
				isMove = true;
			}
			if(isValidPosition(xNumber, yNumber+1) && isValidCoordinateForPath(movedPositions ,xNumber ,yNumber+1) 
					&& isValidCoordinateForPath(cancelledPositions ,xNumber ,yNumber+1)) {
				movedPositions.push(new Coordinate(xNumber, yNumber+1));
				isMove = true;
			}
			
			if (!isMove && !movedPositions.isEmpty()) {
				cancelledPositions.push(movedPositions.pop());
			}
			if(movedPositions.isEmpty()) {
				for (int i = 0; i < 3; i++) {
					Coordinate rnd = randomMovement(numberCoordinate);
					movedPositions.push(rnd);
				}
				xNumber = ((Coordinate) movedPositions.peek()).getX();
				yNumber = ((Coordinate) movedPositions.peek()).getY();
			}
			if(cancelledPositions.isFull()) {
				Coordinate rnd = randomMovement(numberCoordinate);
				numberCoordinate.setPosition(rnd);
				return;
			}
			else {
				xNumber = ((Coordinate)(movedPositions.peek())).getX();
				yNumber = ((Coordinate)(movedPositions.peek())).getY();
			}
			if (xNumber == px && yNumber == py) {
				Stack tempStack = new Stack(1000);
				tempStack.push(movedPositions.pop());
				while(!movedPositions.isEmpty()) {
					if(distance( ((Coordinate) tempStack.peek()).getX(),  ((Coordinate) tempStack.peek()).getY(), ((Coordinate) movedPositions.peek()).getX(),  ((Coordinate) movedPositions.peek()).getY()) == 1) {
						tempStack.push(movedPositions.pop());
					}
					else {
						movedPositions.pop();
					}
				}
				while(!tempStack.isEmpty()) {
					movedPositions.push(tempStack.pop());
				}
				numberCoordinate.setPath(movedPositions);
				break;
			}
		}	
	}

	
	public void checkPath(Number redNumber) {
		Stack path=redNumber.getPath();
		Stack tempPath=new Stack(1000);
		int countDeleted=0;
		Coordinate firstCoordinate=(Coordinate) path.pop();
		tempPath.push(firstCoordinate);
		while (!(path.isEmpty())) {
			Coordinate coordinateElement=(Coordinate) path.pop();
			tempPath.push(coordinateElement);
			if (firstCoordinate.isEqual(coordinateElement)) {
				countDeleted++;
			}else {
				break;
			}
		}
		
		//Recovering path
		while (!tempPath.isEmpty()) {
			path.push(tempPath.pop());
		}
		
		//Deleting 
		for (int i = 0; i < countDeleted; i++) {
			path.pop();
		}
		
		//Adding random movement to the path
		Coordinate realCoordinate=redNumber.getPosition();
		redNumber.setPosition(((Coordinate) redNumber.getPath().peek()));
		if (countDeleted>0) {
			for (int i = 0; i < 3; i++) {
				Coordinate randomMovement=randomMovement(redNumber);
				boolean succed=redNumber.addPath(randomMovement);
				if (!succed) break;
				redNumber.setPosition(randomMovement);
			}
		}
		redNumber.setPosition(realCoordinate);
	}
	
	
	public void showPath(Stack path) {
		Stack tempPath=new Stack(1000);
		while (!(path.isEmpty())) {
			Coordinate coordinate=(Coordinate) path.pop();
			cn.getTextWindow().output(coordinate.getX(), coordinate.getY(), '.');
			tempPath.push(coordinate);
		}
		while (!(tempPath.isEmpty())) {
			path.push(tempPath.pop());
		}
	}
	
	public boolean isValidCoordinateForPath(Stack stack, int x,int y) {
		boolean isValid=true;
		Stack tempQueue=new Stack(1000);
		while (!(stack.isEmpty())) {
			Coordinate element=(Coordinate) stack.pop();
			tempQueue.push(element);
			if (element.getX()==x && element.getY()==y) {
				isValid=false;
			}
		}
		
		while (!(tempQueue.isEmpty())) {
			stack.push(tempQueue.pop());
		}
		return isValid;
	}
	
	public void setPathsRed() {
		for (int i = 0; i < numberCounter; i++) {
			if (numbers[i]!=null && numbers[i].getColor()=='R') { 
				if (numbers[i].getPath()!=null) {
					removeDots(numbers[i]);
					numbers[i].removePath();
					if (!difficulty) {
						findPath(numbers[i].getPosition().getX(), numbers[i].getPosition().getY(),numbers[i]); 
					} else {
						basicFindPath(numbers[i].getPosition().getX(), numbers[i].getPosition().getY(),numbers[i]);
					}
					showPath(numbers[i].getPath());
				    } 
			}
		}
	}
	

	public Coordinate generateBestOption(int xNumber, int yNumber,Number number) {
		Coordinate movement=new Coordinate(xNumber,yNumber);
		int distance=1000;
		if(isValidPosition(xNumber, yNumber+1) && distance(px,py,xNumber,yNumber+1)<distance &&
				isValidCoordinateForPath(number.getPath() ,xNumber,yNumber+1)) {
			movement=new Coordinate(xNumber,yNumber+1);
			distance=distance(px,py,xNumber,yNumber+1);
		}
		
		if(isValidPosition(xNumber, yNumber-1) && distance(px,py,xNumber,yNumber-1)<distance &&
				isValidCoordinateForPath(number.getPath() ,xNumber,yNumber-1)) {
			movement=new Coordinate(xNumber,yNumber-1);
			distance=distance(px,py,xNumber,yNumber-1);
		}
		if (isValidPosition(xNumber+1,yNumber) && distance(px,py,xNumber+1,yNumber)<distance && 
				isValidCoordinateForPath(number.getPath() ,xNumber+1,yNumber)){
			movement=new Coordinate(xNumber+1,yNumber);
			distance=distance(px,py,xNumber+1,yNumber);
		}
		
		if(isValidPosition(xNumber-1, yNumber) && distance(px,py,xNumber-1,yNumber)<distance &&
				isValidCoordinateForPath(number.getPath() ,xNumber-1,yNumber)) {
			movement=new Coordinate(xNumber-1,yNumber);
			distance=distance(px,py,xNumber-1,yNumber);
		}
		return movement;
	}

	
	public void movements() {
		for (int i = 0; i < numberCounter; i++) {
			if (numbers[i] != null && numbers[i].getColor() == 'Y') {
				Coordinate newPosition=randomMovement(numbers[i]);
				numbers[i].setPosition(newPosition);
			}
			if(numbers[i]!= null && numbers[i].getColor() == 'R') {			//new generated red numbers movement
				showPath(numbers[i].getPath());
			}
		}

		showNumbers();
	}
	
	public Coordinate randomMovement(Number n) {
		
		Random rnd = new Random();
		int xx=n.getPosition().getX();
		int yy=n.getPosition().getY();
		int counter = 0;
		while(true) {
			int x = xx;
			int y = yy;
			int path = 0;
	    	cn.getTextWindow().output(x,y,' ');
	    	if (n.getColor() == 'Y' && difficulty) {
	    		path = n.getYellowPath();
			}
	    	else {
	    		path = rnd.nextInt(4);
	    	}
			switch (path) {
				case 0: 
					y += 1;
					break;
				case 1: 
					y -= 1;
					break;
				case 2: 
					x += 1;
					break;
				case 3: 
					x -= 1;
					break;
			}
			if (isValidPosition(x,y)) {
				return (new Coordinate(x,y));
			}
			else if(n.getColor() == 'Y' && difficulty) {
				n.setPathYellow();
			}
			counter++;
			if(counter == 20) {
				return n.getPosition();
			}
		}
	}
	
	public void nextNumber() {
		addInput();
		showNextNumber();
	}

	public void showNextNumber() {
		for (int i = 0; i < numberCounter; i++) {
			if (numbers[i] == null) {
				numbers[i] = (Number) inputs.dequeue();
				if(numbers[i].getColor() == 'R') {
					setPathsRed();
				} 
				TextAttributes attrs = arrangeColor(i);
				cn.getTextWindow().output(numbers[i].getPosition().getX(), numbers[i].getPosition().getY(),
						String.valueOf(numbers[i].getValue()).charAt(0), attrs);
				break;
			}
		}
		cn.getTextWindow().setCursorPosition(57, 2);
		for (int i = 0; i < 10; i++) {
			System.out.print(((Number) inputs.peek()).getValue());
			inputs.enqueue(inputs.dequeue());
		}
	}

	public void addInput() {
		Number nextInput = generateRandomNumber();
		this.inputs.enqueue(nextInput);
	}

	public void createInputs() {
		for (int i = 0; i < 10; i++) {
			Number nextInput = generateRandomNumber();
			this.inputs.enqueue(nextInput);
		}
		cn.getTextWindow().setCursorPosition(57, 2);
		for (int i = 0; i < 10; i++) {
			Number a = (Number) inputs.peek();
			System.out.print(a.getValue());
			inputs.enqueue(inputs.dequeue());
		}
	}
	////////////////////////////////////////////////////
	

	public boolean checkCollision(int xGone, int yGone) {
		// Searching with the function for check whether there is a number or not
		int searchForNumber = searchForNumber(xGone, yGone);
		if (searchForNumber == -1)
			return false;

		// If there is a number,
		if (numbers[searchForNumber].getValue() <= playerNumber) { // --/----------//----------/--//
			// Number is taken by the player
			// First checking whether the leftpack is already filled
			Stack leftPack = backpacks.getLeftPack();
			if (leftPack.isFull())
				leftPack.pop();

			leftPack.push(numbers[searchForNumber].getValue());
			backpacks.showPacks();
			// cn.getTextWindow().setCursorPosition(numbers[searchForNumber].getxPosition(),
			// numbers[searchForNumber].getyPosition());
			cn.getTextWindow().output(numbers[searchForNumber].getPosition().getX(), numbers[searchForNumber].getPosition().getY(),
					' ');
			numbers[searchForNumber] = null;
			// Also, calling the function which check score by investigating packs
			backpacks.checkMatchForPacks();
		} else {
			// The player dies
			cn.getTextWindow().setCursorPosition(5, 25);
			cn.getTextWindow().output("Game is over." + "      Score: " + score);
			return true;
		}
		return false;
	}
	//////////////////////////////////////

	public int searchForNumber(int xGone, int yGone) {
		for (int i = 0; i < numberCounter; i++) {
			if (numbers[i] != null && numbers[i].getPosition().getX() == xGone && numbers[i].getPosition().getY() == yGone) {
				return i;
			}
		}
		return -1;
	}


	public void showTime() {
		cn.getTextWindow().setCursorPosition(64, 22);;
		cn.getTextWindow().output(String.valueOf(time));
	}

	public void showNumbers() {
		for (int i = 0; i < numberCounter; i++) {
			if (numbers[i] != null) {
				TextAttributes attrs = arrangeColor(i);
				cn.getTextWindow().output(numbers[i].getPosition().getX(), numbers[i].getPosition().getY(),String.valueOf(numbers[i].getValue()).charAt(0), attrs);
			}
			
		}
	}

	
	public TextAttributes arrangeColor(int i) {
		TextAttributes attrs = new TextAttributes(Color.white);
		if (numbers[i].getColor() == 'R') {
			attrs = new TextAttributes(Color.RED);
		} else if (numbers[i].getColor() == 'Y') {
			attrs = new TextAttributes(Color.YELLOW);
		} else {
			attrs = new TextAttributes(Color.GREEN);
		}
		return attrs;
	}

	public void settingGameArea(String line, int row) {
		char[] characters = line.toCharArray();
		for (int i = 0; i < 55; i++) {
			if (characters[i] == '#') {
				this.walls[row][i] = 1;
				TextAttributes attrs=new TextAttributes(Color.DARK_GRAY,Color.DARK_GRAY);
				cn.getTextWindow().output(i, row, '#', attrs);
			}
		}
	}

	public void settingRandomNumber() {
		for (int i = 0; i < 25; i++) {
			numbers[i] = generateRandomNumber();
		}
	}

	public Number generateRandomNumber() {
		this.numberCounter++;
		Random random = new Random();
		int probabilityRandom = random.nextInt(100) + 1;
		int xPosition = 0;
		int yPosition = 0;
		while (true) {
			xPosition = random.nextInt(53) + 1;
			yPosition = random.nextInt(21) + 1;
			if (isValidPosition(xPosition, yPosition) && xPosition != this.px && yPosition != this.py) {
				break;
			}
		}

		if (probabilityRandom < 6) {
			int numberValue = random.nextInt(3) + 7;
			return (new Number('R', xPosition, yPosition, numberValue,10000));
		} else if (probabilityRandom < 26) {
			int numberValue = random.nextInt(3) + 4;
			Number n = new Number('Y', xPosition, yPosition, numberValue);
			n.setPathYellow();
			return (n);
		} else {
			int numberValue = random.nextInt(3) + 1;
			return (new Number('G', xPosition, yPosition, numberValue));
		}
	}

	public boolean isValidPosition(int x, int y) {
		if (x>54 || y>22) return false;
		if (walls[y][x] == 1) return false;
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] != null && x == numbers[i].getPosition().getX() && numbers[i].getPosition().getY() == y) {
				return false;
			}
		}
		
		return true;
	}
	public static int getScore() {
		return score;
	}
	public static void setScore(int score) {
		NumberMaze.score = score;
	}
	public static int getPlayerNumber() {
		return playerNumber;
	}
	public static void setPlayerNumber(int playerNumber) {
		NumberMaze.playerNumber = playerNumber;
	}
	public static int getTime() {
		return time;
	}
	public static void setTime(int time) {
		NumberMaze.time = time;
	}
	public static enigma.console.Console getCn() {
		return cn;
	}
	public static void setCn(enigma.console.Console cn) {
		NumberMaze.cn = cn;
	}
	public static int getCounterMakingTwo() {
		return counterMakingTwo;
	}
	public static void setCounterMakingTwo(int counterMakingTwo) {
		NumberMaze.counterMakingTwo = counterMakingTwo;
	}
}