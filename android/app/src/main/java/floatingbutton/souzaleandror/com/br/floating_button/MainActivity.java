package floatingbutton.souzaleandror.com.br.floating_button;

import android.os.Bundle;
import android.widget.ImageView;

import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.Screen;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {

  private static final String CHANNEL = "floating_button";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);

    MethodChannel channel = new MethodChannel(getFlutterView(), CHANNEL);

    channel.setMethodCallHandler((call, result) -> {
      switch (call.method){
        case "create":

          ImageView imageView = new ImageView(getApplicationContext());
          imageView.setImageResource(R.drawable.button);

          FloatWindow
                  .with(getApplicationContext())
                  .setView(imageView)
                  .setWidth(100)
                  .setHeight(Screen.width,0.2f)
                  .setX(100)
                  .setY(Screen.height,0.3f)
                  .setDesktopShow(true)
                  .build();

          imageView.setOnClickListener(v -> channel.invokeMethod("touch", null));

          break;
        case "show":
          FloatWindow.get().show();
          break;
        case "hide":
          FloatWindow.get().hide();
          break;
        case "isShowing":
          result.success(FloatWindow.get().isShowing());
          break;
          default:
            result.notImplemented();
      }
    }
    );
  }

  @Override
  protected void onDestroy() {
    FloatWindow.destroy();
    super.onDestroy();
  }
}
