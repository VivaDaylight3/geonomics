package vivadaylight3.myrmecology.common.handler;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyBindingHandler extends KeyHandler {

    private String name;
    private EnumSet tickType = EnumSet.of(TickType.CLIENT);
    public static boolean keyHasBeenPressed = false;
    public static boolean keyPressed = false;

    public KeyBindingHandler(String parName, KeyBinding[] keyBindings,
	    boolean[] repeatings) {
	super(keyBindings, repeatings);
	name = parName;
    }

    public KeyBindingHandler(KeyBinding[] keyBindings) {
	super(keyBindings);
    }

    @Override
    public String getLabel() {
	return name;
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb,
	    boolean tickEnd, boolean isRepeat) {

    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {

	keyPressed = true;

    }

    @Override
    public EnumSet<TickType> ticks() {
	return tickType;
    }

}
