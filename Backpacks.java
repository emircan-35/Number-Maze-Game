public class Backpacks {
	private Stack leftPack;
	private Stack rightPack;
	public Backpacks(){
		this.leftPack = new Stack(8);
		this.rightPack = new Stack(8);
		
	}
	public void checkMatchForPacks() {
		// For comparison, creating temp stacks
		Stack tempLeftPack = new Stack(this.leftPack.size());
		Stack tempRightPack = new Stack(this.rightPack.size());

		// Assignments for temp stacks
		while (!(this.leftPack.isEmpty())) {
			tempLeftPack.push(this.leftPack.pop());
		}
		while (!(this.rightPack.isEmpty())) {
			tempRightPack.push(this.rightPack.pop());
		}

		// Now, searching for matched elements
		while ((!(tempLeftPack.isEmpty())) && (!(tempRightPack.isEmpty()))) {
			int elementRightPack = (int) tempRightPack.pop();
			int elementLeftPack = (int) tempLeftPack.pop();
			if (elementLeftPack == elementRightPack) {
				int pN = NumberMaze.getPlayerNumber();
				int cMT = NumberMaze.getCounterMakingTwo();
				pN++;
				if (pN>9) {
					pN=1;
					cMT=NumberMaze.getTime();
				}
				int tscore = NumberMaze.getScore();
				tscore += elementLeftPack * findScoreFactor(elementLeftPack);
				NumberMaze.setCounterMakingTwo(cMT);
				NumberMaze.setPlayerNumber(pN);
				NumberMaze.setScore(tscore);
				NumberMaze.getCn().getTextWindow().setCursorPosition(64, 20);
				NumberMaze.getCn().getTextWindow().output(String.valueOf(tscore));
				showPacks();
			} else {
				this.leftPack.push(elementLeftPack);
				this.rightPack.push(elementRightPack);
			}
		}

		// If any of packs is not empty,
		if (!(tempLeftPack.isEmpty())) {
			while (!(tempLeftPack.isEmpty())) {
				this.leftPack.push(tempLeftPack.pop());
			}
		}

		if (!(tempRightPack.isEmpty())) {
			while (!(tempRightPack.isEmpty())) {
				this.rightPack.push(tempRightPack.pop());
			}
		}
		showPacks();
	}

	public int findScoreFactor(int value) {
		if (value < 4) {
			return 1;
		} else if (value < 7) {
			return 5;
		} else {
			return 25;
		}
	}

	public void packQOperation() {
		// Checking whether the right pack has one element at least
		if (!(rightPack.isEmpty())) {
			if (leftPack.isFull()) {
				leftPack.pop();
			}
			leftPack.push(rightPack.pop());
		} else {
			// Impossible
		}
		// Also, calling the function which check score by investigating packs
		checkMatchForPacks();
		showPacks();
	}

	public void packWOperation() {
		// Checking whether the left pack has one element at least
		if (!(leftPack.isEmpty())) {
			if (rightPack.isFull()) {
				rightPack.pop();
			}
			rightPack.push(leftPack.pop());
		} else {
			// Impossible
		}
		// Also, calling the function which check score by investigating packs
		checkMatchForPacks();
		showPacks();
	}
	public void removePackArea() {
		int x = 59;
		int y = 14;

		for (int i = 0; i < 8; i++) {
			NumberMaze.getCn().getTextWindow().output(x, y - i, ' ');
		}
		x = 65;
		y = 14;
		for (int i = 0; i < 8; i++) {
			NumberMaze.getCn().getTextWindow().output(x, y - i, ' ');
		}
	}

	public void showPacks() {
		removePackArea();
		Stack leftTempStack = new Stack(this.leftPack.size());
		Stack rightTempStack = new Stack(this.rightPack.size());

		int x = 59;
		int y = 15 - leftPack.size();

		while (!(this.leftPack.isEmpty())) {
			int element = (int) leftPack.pop();
			leftTempStack.push(element);
			NumberMaze.getCn().getTextWindow().output(x, y, String.valueOf(element).charAt(0));
			y++;
		}
		x = 65;
		y = 15 - rightPack.size();
		while (!(this.rightPack.isEmpty())) {
			int element = (int) rightPack.pop();
			rightTempStack.push(element);
			NumberMaze.getCn().getTextWindow().output(x, y, String.valueOf(element).charAt(0));
			y++;
		}

		while (!(leftTempStack.isEmpty())) {
			this.leftPack.push(leftTempStack.pop());
		}
		while (!(rightTempStack.isEmpty())) {
			this.rightPack.push(rightTempStack.pop());
		}

	}
	public Stack getLeftPack() {
		return leftPack;
	}
	public void setLeftPack(Stack leftPack) {
		this.leftPack = leftPack;
	}
	public Stack getRightPack() {
		return rightPack;
	}
	public void setRightPack(Stack rightPack) {
		this.rightPack = rightPack;
	}
	
}
