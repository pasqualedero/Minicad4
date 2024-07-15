package command;

import memento.MementoCapsule;

import java.util.Stack;

public class HistoryCommandHandler implements CommandHandler {


	private int maxHistoryLength = 100;

	private final Stack<MementoCapsule> stackHistory = new Stack<>();

	public HistoryCommandHandler() {
		this(100);
	}

	public HistoryCommandHandler(int maxHistoryLength) {

		if (maxHistoryLength < 0)
			throw new IllegalArgumentException();
		this.maxHistoryLength = maxHistoryLength;
	}

	public void handle(Command cmd) {
		if (cmd.getGraphicObject()!=null) {

			MementoCapsule memento = new MementoCapsule(cmd.getGraphicObject(), cmd.getGraphicObject().getMemento());

			if (cmd.doIt()) {
				addToHistory(memento);
			} else if (!(cmd instanceof DeclarativeCommand)) {
				stackHistory.clear();
			}

		} else cmd.doIt();
	}


	public void undo() {
		if (stackHistory.size()>0) {
			MementoCapsule m = stackHistory.pop();
			m.getGo().setMemento(m.getMemento());
		}
	}

	public void addToHistory(MementoCapsule mc){
		if (stackHistory.size()>=maxHistoryLength)
			stackHistory.remove(maxHistoryLength-1);
		stackHistory.push(mc);
	}

	public int getStackHistorySize(){
		return stackHistory.size();
	}

	public MementoCapsule getTopItemFromHistory(){
		return stackHistory.peek();
	}

}
