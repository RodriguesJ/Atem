package net.openrs.cache.def;

import java.nio.ByteBuffer;

import net.openrs.util.ByteBufferUtils;

/**
 * A class that loads item/model information from the cache.
 * @author Graham
 * @author `Discardedx2
 *
 * TODO Finish some of the opcodes.
 */
public class ItemDefinition {

	private String name;

	private int inventoryModelId;
	private int modelZoom;
	private int modelRotation1;
	private int modelRotation2;
	private int modelOffset1;
	private int modelOffset2;

	private int stackable;
	private int value;
	private boolean membersOnly;

	private int maleWearModel1;
	private int maleWearModel2;
	private int femaleWearModel1;
	private int femaleWearModel2;

	private String[] groundOptions;
	private String[] inventoryOptions;

	private short[] originalModelColors;
	private short[] modifiedModelColors;
	private short[] textureColour1;
	private short[] textureColour2;
	private boolean unnoted;

	private int colourEquip1;
	private int colourEquip2;
	private int notedId;
	private int notedTemplateId;
	private int[] stackableIds;
	private int[] stackableAmounts;
	private int teamId;
	private int lendId;
	private int lendTemplateId;

	/**
	 * 
	 * @param buffer A {@link ByteBuffer} that contains information
	 * such as the items location.
	 * @return a new ItemDefinition.
	 */
	@SuppressWarnings("unused")
	public static ItemDefinition decode(ByteBuffer buffer) {
		ItemDefinition def = new ItemDefinition();
		def.groundOptions = new String[] { null, null, "take", null, null };
		def.inventoryOptions = new String[] { null, null, null, null, "drop" };
		while (true) {
			int opcode = buffer.get() & 0xFF;
			if (opcode == 0)
				break;
			if(opcode == 1)
				def.inventoryModelId = buffer.getShort() & 0xFFFFF;
			else if (opcode == 2)
				def.name = ByteBufferUtils.getJagexString(buffer);
			else if (opcode == 4)
				def.modelZoom = buffer.getShort() & 0xFFFFF;
			else if (opcode == 5)
				def.modelRotation1 = buffer.getShort() & 0xFFFFF;
			else if (opcode == 6)
				def.modelRotation2 = buffer.getShort() & 0xFFFFF;
			else if (opcode == 7) {
				def.modelOffset1 = buffer.getShort() & 0xFFFFF;
				if (def.modelOffset1 > 32767)
					def.modelOffset1 -= 65536;
				def.modelOffset1 <<= 0;
			}else if (opcode == 8) {
				def.modelOffset2 = buffer.getShort() & 0xFFFFF;
				if (def.modelOffset2 > 32767)
					def.modelOffset2 -= 65536;
				def.modelOffset2 <<= 0;
			}else if (opcode == 11)
				def.stackable = 1;
			else if (opcode == 12)
				def.value = buffer.getInt();
			else if (opcode == 16)
				def.membersOnly = true;
			else if (opcode == 23)
				def.maleWearModel1 = buffer.getShort() & 0xFFFFF;
			else if (opcode == 24)
				def.femaleWearModel1 = buffer.getShort() & 0xFFFFF;
			else if (opcode == 25)
				def.maleWearModel2 = buffer.getShort() & 0xFFFFF;
			else if (opcode == 26)
				def.femaleWearModel2 = buffer.getShort() & 0xFFFFF;
			else if (opcode >= 30 && opcode < 35)
				def.groundOptions[opcode-30] = ByteBufferUtils.getJagexString(buffer);
			else if (opcode >= 35 && opcode < 40)
				def.inventoryOptions[opcode-35] = ByteBufferUtils.getJagexString(buffer);
			else if (opcode == 40) {
				int length = buffer.get() & 0xFF;
				def.originalModelColors = new short[length];
				def.modifiedModelColors = new short[length];
				for(int index = 0; index < length; index++) {
					def.originalModelColors[index] = (short) (buffer.getShort() & 0xFFFFF);
					def.modifiedModelColors[index] = (short) (buffer.getShort() & 0xFFFFF);
				}
			}else if (opcode == 41) {
				int length = buffer.get() & 0xFF;
				def.textureColour1 = new short[length];
				def.textureColour2 = new short[length];
				for(int index = 0; index < length; index++) {
					def.textureColour1[index] = (short) (buffer.getShort() & 0xFFFFF);
					def.textureColour2[index] = (short) (buffer.getShort() & 0xFFFFF);
				}
			} else if (opcode == 42) {
				int length = buffer.get() & 0xFF;
				for(int index = 0; index < length; index++) {
					int i = buffer.get();
				}
			} else if (opcode == 65) {
				def.unnoted = true;
			} else if (opcode == 78) {
				def.colourEquip1 = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 79) {
				def.colourEquip2 = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 90){
				int i = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 91){
				int i = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 92){
				int i = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 93){
				int i = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 95){
				int i = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 96){
				int i = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 97){
				def.notedId = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 98){
				def.notedTemplateId = buffer.getShort() & 0xFFFFF;
			} else if (opcode >= 100 && opcode < 110) {
				if (def.stackableIds == null) {
					def.stackableIds = new int[10];
					def.stackableAmounts = new int[10];
				}
				def.stackableIds[opcode-100] = buffer.getShort() & 0xFFFFF;
				def.stackableAmounts[opcode-100] = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 110) {
				int i = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 111) {
				int i = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 112) {
				int i = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 113) {
				int i = buffer.get();
			} else if (opcode == 114) {
				int i = buffer.get() * 5;
			} else if (opcode == 115) {
				def.teamId = buffer.get() & 0xFF;
			} else if (opcode == 121) {
				def.lendId = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 122) {
				def.lendTemplateId = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 125) {
				int i = buffer.get() << 0;
				int i2 = buffer.get() << 0;
				int i3 = buffer.get() << 0;
			} else if (opcode == 126) {
				int i = buffer.get() << 0;
				int i2 = buffer.get() << 0;
				int i3 = buffer.get() << 0;
			} else if (opcode == 127) {
				int i = buffer.get() & 0xFF;
				int i2 = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 128) {
				int i = buffer.get() & 0xFF;
				int i2 = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 129) {
				int i = buffer.get() & 0xFF;
				int i2 = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 130) {
				int i = buffer.get() & 0xFF;
				int i2 = buffer.getShort() & 0xFFFFF;
			} else if (opcode == 132) {
				int len = buffer.get() & 0xFF;
				for(int index = 0; index < len; index++) {
					int anInt = buffer.getShort() & 0xFFFFF;
				}
			} else if (opcode == 249) {
				int length = buffer.get() & 0xFF;
				for (int index = 0; index < length; index++) {
					boolean stringInstance = buffer.get() == 1;
					int key = ByteBufferUtils.getTriByte(buffer);
					Object value = stringInstance ? ByteBufferUtils.getJagexString(buffer) : buffer.getInt();
				}
			}
		}
		return def;
	}

	public String getName() {
		return name;
	}

	public int getInventoryModelId() {
		return inventoryModelId;
	}

	public int getModelZoom() {
		return modelZoom;
	}

	public int getModelRotation1() {
		return modelRotation1;
	}

	public int getModelRotation2() {
		return modelRotation2;
	}

	public int getModelOffset1() {
		return modelOffset1;
	}

	public int getModelOffset2() {
		return modelOffset2;
	}

	public int getStackable() {
		return stackable;
	}

	public int getValue() {
		return value;
	}

	public boolean isMembersOnly() {
		return membersOnly;
	}

	public int getMaleWearModel1() {
		return maleWearModel1;
	}

	public int getMaleWearModel2() {
		return maleWearModel2;
	}

	public int getFemaleWearModel1() {
		return femaleWearModel1;
	}

	public int getFemaleWearModel2() {
		return femaleWearModel2;
	}

	public String[] getGroundOptions() {
		return groundOptions;
	}

	public String[] getInventoryOptions() {
		return inventoryOptions;
	}

	public short[] getOriginalModelColors() {
		return originalModelColors;
	}

	public short[] getModifiedModelColors() {
		return modifiedModelColors;
	}

	public short[] getTextureColour1() {
		return textureColour1;
	}

	public short[] getTextureColour2() {
		return textureColour2;
	}

	public boolean isUnnoted() {
		return unnoted;
	}

	public int getColourEquip1() {
		return colourEquip1;
	}

	public int getColourEquip2() {
		return colourEquip2;
	}

	public int getNotedId() {
		return notedId;
	}

	public int getNotedTemplateId() {
		return notedTemplateId;
	}

	public int[] getStackableIds() {
		return stackableIds;
	}

	public int[] getStackableAmounts() {
		return stackableAmounts;
	}

	public int getTeamId() {
		return teamId;
	}

	public int getLendId() {
		return lendId;
	}

	public int getLendTemplateId() {
		return lendTemplateId;
	}

}
