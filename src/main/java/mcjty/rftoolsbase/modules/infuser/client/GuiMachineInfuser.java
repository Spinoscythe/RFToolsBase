package mcjty.rftoolsbase.modules.infuser.client;

import mcjty.lib.container.GenericContainer;
import mcjty.lib.gui.GenericGuiContainer;
import mcjty.lib.gui.Window;
import mcjty.lib.gui.widgets.EnergyBar;
import mcjty.lib.gui.widgets.Panel;
import mcjty.rftoolsbase.RFToolsBase;
import mcjty.rftoolsbase.modules.infuser.MachineInfuserModule;
import mcjty.rftoolsbase.modules.infuser.blocks.MachineInfuserTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;

import static mcjty.lib.gui.widgets.Widgets.positional;

public class GuiMachineInfuser extends GenericGuiContainer<MachineInfuserTileEntity, GenericContainer> {
    public static final int INFUSER_WIDTH = 180;
    public static final int INFUSER_HEIGHT = 152;

    private EnergyBar energyBar;

    private static final ResourceLocation iconLocation = new ResourceLocation(RFToolsBase.MODID, "textures/gui/infuser.png");

    public GuiMachineInfuser(MachineInfuserTileEntity te, GenericContainer container, PlayerInventory inventory) {
        super(te, container, inventory, MachineInfuserModule.MACHINE_INFUSER.get().getManualEntry());

        xSize = INFUSER_WIDTH;
        ySize = INFUSER_HEIGHT;
    }

    public static void register() {
        register(MachineInfuserModule.CONTAINER_MACHINE_INFUSER.get(), GuiMachineInfuser::new);
    }

    @Override
    public void init() {
        super.init();

        energyBar = new EnergyBar().name("energybar").vertical().hint(10, 7, 8, 54).showText(false);

        Panel toplevel = positional().background(iconLocation).children(energyBar); //.addChild(arrow);
        toplevel.bounds(guiLeft, guiTop, xSize, ySize);

        window = new Window(this, toplevel);

        initializeFields();
    }

    private void initializeFields() {
        energyBar = window.findChild("energybar");
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float v, int i, int i2) {
        drawWindow();
        updateEnergyBar(energyBar);
    }
}
