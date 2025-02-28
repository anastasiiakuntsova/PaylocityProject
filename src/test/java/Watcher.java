import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class Watcher implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("Test  " + context.getDisplayName() + " completed successfully");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("Test  " + context.getDisplayName() + " failed");
        System.out.println("Error: " + cause.getMessage());
    }

}
