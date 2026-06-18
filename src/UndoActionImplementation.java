public class UndoActionImplementation {

    private UndoAction[] stack;
    private int top;

    public UndoActionImplementation(int size) {
        stack = new UndoAction[size];
        top = -1;
    }

    public void push(UndoAction action) {
        if (top == stack.length - 1) {
            System.out.println("Undo stack is full.");
            return;
        }

        top++;
        stack[top] = action;
    }

    public UndoAction pop() {
        if (top == -1) {
            return null;
        }

        UndoAction action = stack[top];
        stack[top] = null;
        top--;

        return action;
    }

    public void undoLastAction() {
        UndoAction action = pop();

        if (action == null) {
            System.out.println("No action to undo.");
        } else {
            System.out.println(action.getUndoMessage());
        }
    }

    public void displayStack() {
        System.out.println("\n--- Recent Actions Stack ---");

        if (top == -1) {
            System.out.println("No recent action.");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}