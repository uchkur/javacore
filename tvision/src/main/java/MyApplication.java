import jexer.*;

import java.io.UnsupportedEncodingException;

public class MyApplication extends jexer.TApplication {

    public MyApplication() throws UnsupportedEncodingException {

        super(BackendType.SWING); // Could also use BackendType.XTERM

        // Create standard menus for File and Window
        addFileMenu();
        addWindowMenu();

        // Add a custom window, see below for its code.  The TWindow
        // constructor will add it to this application.
        new MyWindow(this);
    }
    public static void main(String [] args) {
        try {
            MyApplication app = new MyApplication();
            (new Thread(app)).start();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
class MyWindow extends TWindow {

    public MyWindow(MyApplication application) {
        // See TWindow's API for several constructors.  This one uses the
        // application, title, width, and height.  Note that the window width
        // and height include the borders.  The widgets inside the window
        // will see (0, 0) as the top-left corner inside the borders,
        // i.e. what the window would see as (1, 1).
        super(application, "My Window", 30, 20);

        // See TWidget's API for convenience methods to add various kinds of
        // widgets.  Note that ANY widget can be a container for other
        // widgets: TRadioGroup for example has TRadioButtons as child
        // widgets.

        // We will add a basic label, text entry field, and button.
        addLabel("This is a label", 5, 3);
        addField(5, 5, 20, false, "enter text here");
        // For the button, we will pop up a message box if the user presses
        // it.
        addButton("Press &Me!", 5, 8, new TAction() {
            public void DO() {
                MyWindow.this.messageBox("Box Title", "You pressed me, yay!");
            }
        } );
    }
}
