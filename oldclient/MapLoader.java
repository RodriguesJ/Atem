package com.runescape.client;

final class MapLoader
{
	private static int anInt123 = (int) (Math.random() * 17.0) - 8;
	private final int[] anIntArray124;
	private final int[] anIntArray125;
	private final int[] anIntArray126;
	private final int[] anIntArray127;
	private final int[] anIntArray128;
	private final int[][][] anIntArrayArrayArray129;
	private final byte[][][] aByteArrayArrayArray130;
	static int anInt131;
	private static int anInt133 = (int) (Math.random() * 33.0) - 16;
	private final byte[][][] aByteArrayArrayArray134;
	private final int[][][] anIntArrayArrayArray135;
	private final byte[][][] aByteArrayArrayArray136;
	private static final int[] anIntArray137 = { 1, 0, -1, 0 };
	private static int anInt138 = 323;
	private final int[][] anIntArrayArray139;
	private static final int[] anIntArray140 = { 16, 32, 64, 128 };
	private static boolean aBoolean141;
	private final byte[][][] aByteArrayArrayArray142;
	private boolean aBoolean143 = false;
	private static final int[] anIntArray144 = { 0, -1, 0, 1 };
	static int anInt145 = 99;
	private final int anInt146;
	private final int anInt147;
	private final byte[][][] aByteArrayArrayArray148;
	private final byte[][][] aByteArrayArrayArray149;
	private final int anInt150 = -53;
	static boolean aBoolean151 = true;
	private static final int[] anIntArray152 = { 1, 2, 4, 8 };
	public MapLoader(final byte[][][] is, final int i, final int i_0_, final int i_1_, final int[][][] is_2_) {
		MapLoader.anInt145 = 99;
		this.anInt146 = i_1_;
		this.anInt147 = i_0_;
		while (i >= 0) {
		}
		this.anIntArrayArrayArray129 = is_2_;
		this.aByteArrayArrayArray149 = is;
		this.aByteArrayArrayArray142 = new byte[4][this.anInt146][this.anInt147];
		this.aByteArrayArrayArray130 = new byte[4][this.anInt146][this.anInt147];
		this.aByteArrayArrayArray136 = new byte[4][this.anInt146][this.anInt147];
		this.aByteArrayArrayArray148 = new byte[4][this.anInt146][this.anInt147];
		this.anIntArrayArrayArray135 = new int[4][this.anInt146 + 1][this.anInt147 + 1];
		this.aByteArrayArrayArray134 = new byte[4][this.anInt146 + 1][this.anInt147 + 1];
		this.anIntArrayArray139 = new int[this.anInt146 + 1][this.anInt147 + 1];
		this.anIntArray124 = new int[this.anInt147];
		this.anIntArray125 = new int[this.anInt147];
		this.anIntArray126 = new int[this.anInt147];
		this.anIntArray127 = new int[this.anInt147];
		this.anIntArray128 = new int[this.anInt147];
	}

	private static final int method170(final int i, final int i_3_) {
		int i_4_ = i + (i_3_ * 57);
		i_4_ = (i_4_ << 13) ^ i_4_;
		final int i_5_
		= ((i_4_ * ((i_4_ * i_4_ * 15731) + 789221)) + 1376312589) & 0x7fffffff;
		return (i_5_ >> 19) & 0xff;
	}

	public final void method171(final Class11[] class11s, final Class25 class25, final int i) {
		for (int i_6_ = 0; i_6_ < 4; i_6_++) {
			for (int i_7_ = 0; i_7_ < 104; i_7_++) {
				for (int i_8_ = 0; i_8_ < 104; i_8_++) {
					if ((this.aByteArrayArrayArray149[i_6_][i_7_][i_8_] & 0x1)
							== 1) {
						int i_9_ = i_6_;
						if ((this.aByteArrayArrayArray149[1][i_7_][i_8_] & 0x2)
								== 2) {
							i_9_--;
						}
						if (i_9_ >= 0) {
							class11s[i_9_].method213(i_8_, 0, i_7_);
						}
					}
				}
			}
		}
		if ((i < 2) || (i > 2)) {
			MapLoader.anInt138 = 329;
		}
		MapLoader.anInt123 += (int) (Math.random() * 5.0) - 2;
		if (MapLoader.anInt123 < -8) {
			MapLoader.anInt123 = -8;
		}
		if (MapLoader.anInt123 > 8) {
			MapLoader.anInt123 = 8;
		}
		MapLoader.anInt133 += (int) (Math.random() * 5.0) - 2;
		if (MapLoader.anInt133 < -16) {
			MapLoader.anInt133 = -16;
		}
		if (MapLoader.anInt133 > 16) {
			MapLoader.anInt133 = 16;
		}
		for (int i_10_ = 0; i_10_ < 4; i_10_++) {
			final byte[][] is = this.aByteArrayArrayArray134[i_10_];
			final int i_11_ = 96;
			final int i_12_ = 768;
			final int i_13_ = -50;
			final int i_14_ = -10;
			final int i_15_ = -50;
			final int i_16_ = (int) Math.sqrt(((i_13_ * i_13_) + (i_14_ * i_14_)
					+ (i_15_ * i_15_)));
			final int i_17_ = (i_12_ * i_16_) >> 8;
		for (int i_18_ = 1; i_18_ < (this.anInt147 - 1); i_18_++) {
			for (int i_19_ = 1; i_19_ < (this.anInt146 - 1); i_19_++) {
				final int i_20_
				= (this.anIntArrayArrayArray129[i_10_][i_19_ + 1][i_18_]
						- this.anIntArrayArrayArray129[i_10_][i_19_ - 1][i_18_]);
				final int i_21_
				= (this.anIntArrayArrayArray129[i_10_][i_19_][i_18_ + 1]
						- this.anIntArrayArrayArray129[i_10_][i_19_][i_18_ - 1]);
				final int i_22_ = (int) Math.sqrt(((i_20_ * i_20_) + 65536
						+ (i_21_ * i_21_)));
				final int i_23_ = (i_20_ << 8) / i_22_;
				final int i_24_ = 65536 / i_22_;
				final int i_25_ = (i_21_ << 8) / i_22_;
				final int i_26_ = i_11_ + (((i_13_ * i_23_) + (i_14_ * i_24_)
						+ (i_15_ * i_25_)) / i_17_);
				final int i_27_ = ((is[i_19_ - 1][i_18_] >> 2)
						+ (is[i_19_ + 1][i_18_] >> 3)
						+ (is[i_19_][i_18_ - 1] >> 2)
						+ (is[i_19_][i_18_ + 1] >> 3)
						+ (is[i_19_][i_18_] >> 1));
				this.anIntArrayArray139[i_19_][i_18_] = i_26_ - i_27_;
			}
		}
		for (int i_28_ = 0; i_28_ < this.anInt147; i_28_++) {
			this.anIntArray124[i_28_] = 0;
			this.anIntArray125[i_28_] = 0;
			this.anIntArray126[i_28_] = 0;
			this.anIntArray127[i_28_] = 0;
			this.anIntArray128[i_28_] = 0;
		}
		for (int i_29_ = -5; i_29_ < (this.anInt146 + 5); i_29_++) {
			for (int i_30_ = 0; i_30_ < this.anInt147; i_30_++) {
				final int i_31_ = i_29_ + 5;
				if ((i_31_ >= 0) && (i_31_ < this.anInt146)) {
					final int i_32_
					= (this.aByteArrayArrayArray142[i_10_][i_31_][i_30_]
							& 0xff);
					if (i_32_ > 0) {
						final Class22 class22
						= Class22.aClass22Array388[i_32_ - 1];
						this.anIntArray124[i_30_] += class22.anInt397;
						this.anIntArray125[i_30_] += class22.anInt395;
						this.anIntArray126[i_30_] += class22.anInt396;
						this.anIntArray127[i_30_] += class22.anInt398;
						this.anIntArray128[i_30_]++;
					}
				}
				final int i_33_ = i_29_ - 5;
				if ((i_33_ >= 0) && (i_33_ < this.anInt146)) {
					final int i_34_
					= (this.aByteArrayArrayArray142[i_10_][i_33_][i_30_]
							& 0xff);
					if (i_34_ > 0) {
						final Class22 class22
						= Class22.aClass22Array388[i_34_ - 1];
						this.anIntArray124[i_30_] -= class22.anInt397;
						this.anIntArray125[i_30_] -= class22.anInt395;
						this.anIntArray126[i_30_] -= class22.anInt396;
						this.anIntArray127[i_30_] -= class22.anInt398;
						this.anIntArray128[i_30_]--;
					}
				}
			}
			if ((i_29_ >= 1) && (i_29_ < (this.anInt146 - 1))) {
				int i_35_ = 0;
				int i_36_ = 0;
				int i_37_ = 0;
				int i_38_ = 0;
				int i_39_ = 0;
				for (int i_40_ = -5; i_40_ < (this.anInt147 + 5); i_40_++) {
					final int i_41_ = i_40_ + 5;
					if ((i_41_ >= 0) && (i_41_ < this.anInt147)) {
						i_35_ += this.anIntArray124[i_41_];
						i_36_ += this.anIntArray125[i_41_];
						i_37_ += this.anIntArray126[i_41_];
						i_38_ += this.anIntArray127[i_41_];
						i_39_ += this.anIntArray128[i_41_];
					}
					final int i_42_ = i_40_ - 5;
					if ((i_42_ >= 0) && (i_42_ < this.anInt147)) {
						i_35_ -= this.anIntArray124[i_42_];
						i_36_ -= this.anIntArray125[i_42_];
						i_37_ -= this.anIntArray126[i_42_];
						i_38_ -= this.anIntArray127[i_42_];
						i_39_ -= this.anIntArray128[i_42_];
					}
					if ((i_40_ >= 1) && (i_40_ < (this.anInt147 - 1))
							&& (!MapLoader.aBoolean151
									|| ((this.aByteArrayArrayArray149[0][i_29_][i_40_]
											& 0x2) != 0)
											|| ((((this.aByteArrayArrayArray149[i_10_][i_29_]
													[i_40_])
													& 0x10) == 0)
													&& (this.method182(i_40_, i_10_, i_29_, 0)
															== MapLoader.anInt131)))) {
						if (i_10_ < MapLoader.anInt145) {
							MapLoader.anInt145 = i_10_;
						}
						final int i_43_
						= (this.aByteArrayArrayArray142[i_10_][i_29_][i_40_]
								& 0xff);
						final int i_44_
						= (this.aByteArrayArrayArray130[i_10_][i_29_][i_40_]
								& 0xff);
						if ((i_43_ > 0) || (i_44_ > 0)) {
							final int i_45_ = (this.anIntArrayArrayArray129[i_10_]
									[i_29_][i_40_]);
							final int i_46_ = (this.anIntArrayArrayArray129[i_10_]
									[i_29_ + 1][i_40_]);
							final int i_47_ = (this.anIntArrayArrayArray129[i_10_]
									[i_29_ + 1][i_40_ + 1]);
							final int i_48_ = (this.anIntArrayArrayArray129[i_10_]
									[i_29_][i_40_ + 1]);
							final int i_49_ = this.anIntArrayArray139[i_29_][i_40_];
							final int i_50_
							= this.anIntArrayArray139[i_29_ + 1][i_40_];
							final int i_51_
							= this.anIntArrayArray139[i_29_ + 1][i_40_ + 1];
							final int i_52_
							= this.anIntArrayArray139[i_29_][i_40_ + 1];
							int i_53_ = -1;
							int i_54_ = -1;
							if (i_43_ > 0) {
								int i_55_ = (i_35_ * 256) / i_38_;
								final int i_56_ = i_36_ / i_39_;
								int i_57_ = i_37_ / i_39_;
								i_53_ = this.method177(i_55_, i_56_, i_57_);
								i_55_ = (i_55_ + MapLoader.anInt123) & 0xff;
								i_57_ += MapLoader.anInt133;
								if (i_57_ < 0) {
									i_57_ = 0;
								} else if (i_57_ > 255) {
									i_57_ = 255;
								}
								i_54_ = this.method177(i_55_, i_56_, i_57_);
							}
							if (i_10_ > 0) {
								boolean bool = true;
								if ((i_43_ == 0)
										&& ((this.aByteArrayArrayArray136[i_10_]
												[i_29_][i_40_]) != 0)) {
									bool = false;
								}
								if ((i_44_ > 0)
										&& !(Class22.aClass22Array388
												[i_44_ - 1].aBoolean393)) {
									bool = false;
								}
								if (bool && (i_45_ == i_46_)
										&& (i_45_ == i_47_) && (i_45_ == i_48_)) {
									this.anIntArrayArrayArray135[i_10_]
											[i_29_][i_40_]
													|= 0x924;
								}
							}
							int i_58_ = 0;
							if (i_53_ != -1) {
								i_58_ = (Class30_Sub2_Sub1_Sub3
										.anIntArray1482
										[MapLoader.method187(i_54_, 96)]);
							}
							if (i_44_ == 0) {
								class25.method279(i_10_, i_29_, i_40_, 0,
										0, -1, i_45_, i_46_,
										i_47_, i_48_,
										MapLoader.method187(i_53_, i_49_),
										MapLoader.method187(i_53_, i_50_),
										MapLoader.method187(i_53_, i_51_),
										MapLoader.method187(i_53_, i_52_),
										0, 0, 0, 0, i_58_, 0);
							} else {
								final int i_59_ = ((this.aByteArrayArrayArray136
										[i_10_][i_29_][i_40_])
										+ 1);
								final byte i_60_ = (this.aByteArrayArrayArray148
										[i_10_][i_29_][i_40_]);
								final Class22 class22
								= Class22.aClass22Array388[i_44_ - 1];
								int i_61_ = class22.anInt391;
								int i_62_;
								int i_63_;
								if (i_61_ >= 0) {
									i_62_ = Class30_Sub2_Sub1_Sub3
											.method369(i_61_, 12660);
									i_63_ = -1;
								} else if (class22.anInt390 == 16711935) {
									i_62_ = 0;
									i_63_ = -2;
									i_61_ = -1;
								} else {
									i_63_ = this.method177(class22.anInt394,
											class22.anInt395,
											class22.anInt396);
									i_62_ = (Class30_Sub2_Sub1_Sub3
											.anIntArray1482
											[this.method185(class22.anInt399,
													96)]);
								}
								class25.method279(i_10_, i_29_, i_40_,
										i_59_, i_60_, i_61_,
										i_45_, i_46_, i_47_,
										i_48_,
										MapLoader.method187(i_53_, i_49_),
										MapLoader.method187(i_53_, i_50_),
										MapLoader.method187(i_53_, i_51_),
										MapLoader.method187(i_53_, i_52_),
										this.method185(i_63_, i_49_),
										this.method185(i_63_, i_50_),
										this.method185(i_63_, i_51_),
										this.method185(i_63_, i_52_),
										i_58_, i_62_);
							}
						}
					}
				}
			}
		}
		for (int i_64_ = 1; i_64_ < (this.anInt147 - 1); i_64_++) {
			for (int i_65_ = 1; i_65_ < (this.anInt146 - 1); i_65_++) {
				class25.method278(i_10_, i_65_, i_64_,
						this.method182(i_64_, i_10_, i_65_, 0));
			}
		}
		}
		class25.method305(-10, (byte) 3, 64, -50, 768, -50);
		for (int i_66_ = 0; i_66_ < this.anInt146; i_66_++) {
			for (int i_67_ = 0; i_67_ < this.anInt147; i_67_++) {
				if ((this.aByteArrayArrayArray149[1][i_66_][i_67_] & 0x2) == 2) {
					class25.method276(i_67_, i_66_, -438);
				}
			}
		}
		int i_68_ = 1;
		int i_69_ = 2;
		int i_70_ = 4;
		for (int i_71_ = 0; i_71_ < 4; i_71_++) {
			if (i_71_ > 0) {
				i_68_ <<= 3;
				i_69_ <<= 3;
				i_70_ <<= 3;
			}
			for (int i_72_ = 0; i_72_ <= i_71_; i_72_++) {
				for (int i_73_ = 0; i_73_ <= this.anInt147; i_73_++) {
					for (int i_74_ = 0; i_74_ <= this.anInt146; i_74_++) {
						if ((this.anIntArrayArrayArray135[i_72_][i_74_][i_73_]
								& i_68_)
								!= 0) {
							int i_75_ = i_73_;
							int i_76_ = i_73_;
							int i_77_ = i_72_;
							int i_78_ = i_72_;
							for (/**/; i_75_ > 0; i_75_--) {
								if (((this.anIntArrayArrayArray135[i_72_][i_74_]
										[i_75_ - 1])
										& i_68_)
										== 0) {
									break;
								}
							}
							for (/**/; i_76_ < this.anInt147; i_76_++) {
								if (((this.anIntArrayArrayArray135[i_72_][i_74_]
										[i_76_ + 1])
										& i_68_)
										== 0) {
									break;
								}
							}
							while_6_:
								for (/**/; i_77_ > 0; i_77_--) {
									for (int i_79_ = i_75_; i_79_ <= i_76_;
											i_79_++) {
										if (((this.anIntArrayArrayArray135[i_77_ - 1]
												[i_74_][i_79_])
												& i_68_)
												== 0) {
											break while_6_;
										}
									}
								}
							while_7_:
								for (/**/; i_78_ < i_71_; i_78_++) {
									for (int i_80_ = i_75_; i_80_ <= i_76_;
											i_80_++) {
										if (((this.anIntArrayArrayArray135[i_78_ + 1]
												[i_74_][i_80_])
												& i_68_)
												== 0) {
											break while_7_;
										}
									}
								}
								final int i_81_
								= ((i_78_ + 1) - i_77_) * ((i_76_ - i_75_) + 1);
								if (i_81_ >= 8) {
									final int i_82_ = 240;
									final int i_83_ = ((this.anIntArrayArrayArray129[i_78_]
											[i_74_][i_75_])
											- i_82_);
									final int i_84_ = (this.anIntArrayArrayArray129[i_77_]
											[i_74_][i_75_]);
									Class25.method277(i_71_, i_74_ * 128, i_84_,
											i_74_ * 128,
											(i_76_ * 128) + 128, i_83_,
											this.anInt150, i_75_ * 128, 1);
									for (int i_85_ = i_77_; i_85_ <= i_78_;
											i_85_++) {
										for (int i_86_ = i_75_; i_86_ <= i_76_;
												i_86_++) {
											this.anIntArrayArrayArray135[i_85_]
													[i_74_][i_86_]
															&= i_68_ ^ 0xffffffff;
										}
									}
								}
						}
						if ((this.anIntArrayArrayArray135[i_72_][i_74_][i_73_]
								& i_69_)
								!= 0) {
							int i_87_ = i_74_;
							int i_88_ = i_74_;
							int i_89_ = i_72_;
							int i_90_ = i_72_;
							for (/**/; i_87_ > 0; i_87_--) {
								if (((this.anIntArrayArrayArray135[i_72_][i_87_ - 1]
										[i_73_])
										& i_69_)
										== 0) {
									break;
								}
							}
							for (/**/; i_88_ < this.anInt146; i_88_++) {
								if (((this.anIntArrayArrayArray135[i_72_][i_88_ + 1]
										[i_73_])
										& i_69_)
										== 0) {
									break;
								}
							}
							while_8_:
								for (/**/; i_89_ > 0; i_89_--) {
									for (int i_91_ = i_87_; i_91_ <= i_88_;
											i_91_++) {
										if (((this.anIntArrayArrayArray135[i_89_ - 1]
												[i_91_][i_73_])
												& i_69_)
												== 0) {
											break while_8_;
										}
									}
								}
							while_9_:
								for (/**/; i_90_ < i_71_; i_90_++) {
									for (int i_92_ = i_87_; i_92_ <= i_88_;
											i_92_++) {
										if (((this.anIntArrayArrayArray135[i_90_ + 1]
												[i_92_][i_73_])
												& i_69_)
												== 0) {
											break while_9_;
										}
									}
								}
								final int i_93_
								= ((i_90_ + 1) - i_89_) * ((i_88_ - i_87_) + 1);
								if (i_93_ >= 8) {
									final int i_94_ = 240;
									final int i_95_ = ((this.anIntArrayArrayArray129[i_90_]
											[i_87_][i_73_])
											- i_94_);
									final int i_96_ = (this.anIntArrayArrayArray129[i_89_]
											[i_87_][i_73_]);
									Class25.method277(i_71_, i_87_ * 128, i_96_,
											(i_88_ * 128) + 128,
											i_73_ * 128, i_95_, this.anInt150,
											i_73_ * 128, 2);
									for (int i_97_ = i_89_; i_97_ <= i_90_;
											i_97_++) {
										for (int i_98_ = i_87_; i_98_ <= i_88_;
												i_98_++) {
											this.anIntArrayArrayArray135[i_97_]
													[i_98_][i_73_]
															&= i_69_ ^ 0xffffffff;
										}
									}
								}
						}
						if ((this.anIntArrayArrayArray135[i_72_][i_74_][i_73_]
								& i_70_)
								!= 0) {
							int i_99_ = i_74_;
							int i_100_ = i_74_;
							int i_101_ = i_73_;
							int i_102_ = i_73_;
							for (/**/; i_101_ > 0; i_101_--) {
								if (((this.anIntArrayArrayArray135[i_72_][i_74_]
										[i_101_ - 1])
										& i_70_)
										== 0) {
									break;
								}
							}
							for (/**/; i_102_ < this.anInt147; i_102_++) {
								if (((this.anIntArrayArrayArray135[i_72_][i_74_]
										[i_102_ + 1])
										& i_70_)
										== 0) {
									break;
								}
							}
							while_10_:
								for (/**/; i_99_ > 0; i_99_--) {
									for (int i_103_ = i_101_; i_103_ <= i_102_;
											i_103_++) {
										if (((this.anIntArrayArrayArray135[i_72_]
												[i_99_ - 1][i_103_])
												& i_70_)
												== 0) {
											break while_10_;
										}
									}
								}
							while_11_:
								for (/**/; i_100_ < this.anInt146; i_100_++) {
									for (int i_104_ = i_101_; i_104_ <= i_102_;
											i_104_++) {
										if (((this.anIntArrayArrayArray135[i_72_]
												[i_100_ + 1][i_104_])
												& i_70_)
												== 0) {
											break while_11_;
										}
									}
								}
								if ((((i_100_ - i_99_) + 1) * ((i_102_ - i_101_) + 1))
										>= 4) {
									final int i_105_ = (this.anIntArrayArrayArray129[i_72_]
											[i_99_][i_101_]);
									Class25.method277(i_71_, i_99_ * 128, i_105_,
											(i_100_ * 128) + 128,
											(i_102_ * 128) + 128, i_105_,
											this.anInt150, i_101_ * 128, 4);
									for (int i_106_ = i_99_; i_106_ <= i_100_;
											i_106_++) {
										for (int i_107_ = i_101_; i_107_ <= i_102_;
												i_107_++) {
											this.anIntArrayArrayArray135[i_72_]
													[i_106_][i_107_]
															&= i_70_ ^ 0xffffffff;
										}
									}
								}
						}
					}
				}
			}
		}
	}

	private static final int method172(final int i, final int i_108_) {
		int i_109_ = ((MapLoader.method176(i + 45365, i_108_ + 91923, 4) - 128)
				+ ((MapLoader.method176(i + 10294, i_108_ + 37821, 2) - 128) >> 1)
				+ ((MapLoader.method176(i, i_108_, 1) - 128) >> 2));
		i_109_ = (int) (i_109_ * 0.3) + 35;
		if (i_109_ < 10) {
			i_109_ = 10;
		} else if (i_109_ > 60) {
			i_109_ = 60;
		}
		return i_109_;
	}

	public static final void method173(final byte i,
			final Class30_Sub2_Sub2 class30_sub2_sub2,
			final Class42_Sub1 class42_sub1) {
		int i_110_ = -1;
		if (i != -107) {
			MapLoader.aBoolean141 = !MapLoader.aBoolean141;
		}
		for (;;) {
			final int i_111_ = class30_sub2_sub2.method422();
			if (i_111_ == 0) {
				break;
			}
			i_110_ += i_111_;
			final Class46 class46 = Class46.method572(i_110_);
			class46.method574(class42_sub1, -235);
			for (;;) {
				final int i_112_ = class30_sub2_sub2.method422();
				if (i_112_ == 0) {
					break;
				}
				class30_sub2_sub2.method408();
			}
		}
	}

	public final void method174(final int i, final int i_113_, final int i_114_, final int i_115_,
			final int i_116_) {
		for (int i_117_ = i; i_117_ <= (i + i_113_); i_117_++) {
			for (int i_118_ = i_116_; i_118_ <= (i_116_ + i_115_); i_118_++) {
				if ((i_118_ >= 0) && (i_118_ < this.anInt146) && (i_117_ >= 0)
						&& (i_117_ < this.anInt147)) {
					this.aByteArrayArrayArray134[0][i_118_][i_117_] = (byte) 127;
					if ((i_118_ == i_116_) && (i_118_ > 0)) {
						this.anIntArrayArrayArray129[0][i_118_][i_117_]
								= this.anIntArrayArrayArray129[0][i_118_ - 1][i_117_];
					}
					if ((i_118_ == (i_116_ + i_115_)) && (i_118_ < (this.anInt146 - 1))) {
						this.anIntArrayArrayArray129[0][i_118_][i_117_]
								= this.anIntArrayArrayArray129[0][i_118_ + 1][i_117_];
					}
					if ((i_117_ == i) && (i_117_ > 0)) {
						this.anIntArrayArrayArray129[0][i_118_][i_117_]
								= this.anIntArrayArrayArray129[0][i_118_][i_117_ - 1];
					}
					if ((i_117_ == (i + i_113_)) && (i_117_ < (this.anInt147 - 1))) {
						this.anIntArrayArrayArray129[0][i_118_][i_117_]
								= this.anIntArrayArrayArray129[0][i_118_][i_117_ + 1];
					}
				}
			}
		}
		if (i_114_ != 0) {
		}
	}

	private final void method175(final int i, final Class25 class25, final Class11 class11,
			final int i_119_, final int i_120_, final int i_121_,
			final int i_122_, final boolean bool, final int i_123_) {
		if (!MapLoader.aBoolean151 || ((this.aByteArrayArrayArray149[0][i_121_][i] & 0x2) != 0)
				|| (((this.aByteArrayArrayArray149[i_120_][i_121_][i] & 0x10) == 0)
						&& (this.method182(i, i_120_, i_121_, 0) == MapLoader.anInt131))) {
			if (i_120_ < MapLoader.anInt145) {
				MapLoader.anInt145 = i_120_;
			}
			int i_124_ = this.anIntArrayArrayArray129[i_120_][i_121_][i];
			int i_125_ = this.anIntArrayArrayArray129[i_120_][i_121_ + 1][i];
			int i_126_ = this.anIntArrayArrayArray129[i_120_][i_121_ + 1][i + 1];
			int i_127_ = this.anIntArrayArrayArray129[i_120_][i_121_][i + 1];
			final int i_128_ = (i_124_ + i_125_ + i_126_ + i_127_) >> 2;
			final Class46 class46 = Class46.method572(i_122_);
			int i_129_ = i_121_ + (i << 7) + (i_122_ << 14) + 1073741824;
			if (!class46.aBoolean778) {
				i_129_ += -2147483648;
			}
			final byte i_130_ = (byte) ((i_123_ << 6) + i_119_);
			if (!bool) {
				if (i_119_ == 22) {
					if (!MapLoader.aBoolean151 || class46.aBoolean778
							|| class46.aBoolean736) {
						Class30_Sub2_Sub4 class30_sub2_sub4;
						if ((class46.anInt781 == -1)
								&& (class46.anIntArray759 == null)) {
							class30_sub2_sub4
							= class46.method578(22, i_123_, i_124_, i_125_,
									i_126_, i_127_, -1);
						} else {
							class30_sub2_sub4
							= new Class30_Sub2_Sub4_Sub5(i_122_, i_123_,
									22, i_125_,
									(byte) 7, i_126_,
									i_124_, i_127_,
									class46.anInt781,
									true);
						}
						class25.method280(i_120_, i_128_, i, 68,
								class30_sub2_sub4, i_130_, i_129_,
								i_121_);
						if (class46.aBoolean767 && class46.aBoolean778
								&& (class11 != null)) {
							class11.method213(i, 0, i_121_);
						}
					}
				} else if ((i_119_ == 10) || (i_119_ == 11)) {
					Class30_Sub2_Sub4 class30_sub2_sub4;
					if ((class46.anInt781 == -1)
							&& (class46.anIntArray759 == null)) {
						class30_sub2_sub4
						= class46.method578(10, i_123_, i_124_, i_125_,
								i_126_, i_127_, -1);
					} else {
						class30_sub2_sub4
						= new Class30_Sub2_Sub4_Sub5(i_122_, i_123_, 10,
								i_125_, (byte) 7,
								i_126_, i_124_,
								i_127_,
								class46.anInt781,
								true);
					}
					if (class30_sub2_sub4 != null) {
						int i_131_ = 0;
						if (i_119_ == 11) {
							i_131_ += 256;
						}
						int i_132_;
						int i_133_;
						if ((i_123_ == 1) || (i_123_ == 3)) {
							i_132_ = class46.anInt761;
							i_133_ = class46.anInt744;
						} else {
							i_132_ = class46.anInt744;
							i_133_ = class46.anInt761;
						}
						if (class25.method284(i_129_, i_130_, i_128_, i_133_,
								class30_sub2_sub4, i_132_,
								i_120_, i_131_, (byte) 110, i,
								i_121_)
								&& class46.aBoolean779) {
							Class30_Sub2_Sub4_Sub6 class30_sub2_sub4_sub6;
							if (class30_sub2_sub4
									instanceof Class30_Sub2_Sub4_Sub6) {
								class30_sub2_sub4_sub6
								= ((Class30_Sub2_Sub4_Sub6)
										class30_sub2_sub4);
							} else {
								class30_sub2_sub4_sub6
								= class46.method578(10, i_123_, i_124_,
										i_125_, i_126_, i_127_,
										-1);
							}
							if (class30_sub2_sub4_sub6 != null) {
								for (int i_134_ = 0; i_134_ <= i_132_;
										i_134_++) {
									for (int i_135_ = 0; i_135_ <= i_133_;
											i_135_++) {
										int i_136_
										= (class30_sub2_sub4_sub6.anInt1650
												/ 4);
										if (i_136_ > 30) {
											i_136_ = 30;
										}
										if (i_136_
												> (this.aByteArrayArrayArray134[i_120_]
														[i_121_ + i_134_][i + i_135_])) {
											this.aByteArrayArrayArray134[i_120_]
													[i_121_ + i_134_][i + i_135_]
															= (byte) i_136_;
										}
									}
								}
							}
						}
					}
					if (class46.aBoolean767 && (class11 != null)) {
						class11.method212(class46.aBoolean757, MapLoader.anInt138,
								class46.anInt744, class46.anInt761,
								i_121_, i, i_123_);
					}
				} else if (i_119_ >= 12) {
					Class30_Sub2_Sub4 class30_sub2_sub4;
					if ((class46.anInt781 == -1)
							&& (class46.anIntArray759 == null)) {
						class30_sub2_sub4
						= class46.method578(i_119_, i_123_, i_124_, i_125_,
								i_126_, i_127_, -1);
					} else {
						class30_sub2_sub4
						= new Class30_Sub2_Sub4_Sub5(i_122_, i_123_,
								i_119_, i_125_,
								(byte) 7, i_126_,
								i_124_, i_127_,
								class46.anInt781,
								true);
					}
					class25.method284(i_129_, i_130_, i_128_, 1,
							class30_sub2_sub4, 1, i_120_, 0,
							(byte) 110, i, i_121_);
					if ((i_119_ >= 12) && (i_119_ <= 17) && (i_119_ != 13)
							&& (i_120_ > 0)) {
						this.anIntArrayArrayArray135[i_120_][i_121_][i] |= 0x924;
					}
					if (class46.aBoolean767 && (class11 != null)) {
						class11.method212(class46.aBoolean757, MapLoader.anInt138,
								class46.anInt744, class46.anInt761,
								i_121_, i, i_123_);
					}
				} else if (i_119_ == 0) {
					Class30_Sub2_Sub4 class30_sub2_sub4;
					if ((class46.anInt781 == -1)
							&& (class46.anIntArray759 == null)) {
						class30_sub2_sub4
						= class46.method578(0, i_123_, i_124_, i_125_,
								i_126_, i_127_, -1);
					} else {
						class30_sub2_sub4
						= new Class30_Sub2_Sub4_Sub5(i_122_, i_123_, 0,
								i_125_, (byte) 7,
								i_126_, i_124_,
								i_127_,
								class46.anInt781,
								true);
					}
					class25.method282(MapLoader.anIntArray152[i_123_], class30_sub2_sub4,
							true, i_129_, i, i_130_, i_121_, null,
							i_128_, 0, i_120_);
					if (i_123_ == 0) {
						if (class46.aBoolean779) {
							this.aByteArrayArrayArray134[i_120_][i_121_][i]
									= (byte) 50;
							this.aByteArrayArrayArray134[i_120_][i_121_][i + 1]
									= (byte) 50;
						}
						if (class46.aBoolean764) {
							this.anIntArrayArrayArray135[i_120_][i_121_][i]
									|= 0x249;
						}
					} else if (i_123_ == 1) {
						if (class46.aBoolean779) {
							this.aByteArrayArrayArray134[i_120_][i_121_][i + 1]
									= (byte) 50;
							this.aByteArrayArrayArray134[i_120_][i_121_ + 1][i + 1]
									= (byte) 50;
						}
						if (class46.aBoolean764) {
							this.anIntArrayArrayArray135[i_120_][i_121_][i + 1]
									|= 0x492;
						}
					} else if (i_123_ == 2) {
						if (class46.aBoolean779) {
							this.aByteArrayArrayArray134[i_120_][i_121_ + 1][i]
									= (byte) 50;
							this.aByteArrayArrayArray134[i_120_][i_121_ + 1][i + 1]
									= (byte) 50;
						}
						if (class46.aBoolean764) {
							this.anIntArrayArrayArray135[i_120_][i_121_ + 1][i]
									|= 0x249;
						}
					} else if (i_123_ == 3) {
						if (class46.aBoolean779) {
							this.aByteArrayArrayArray134[i_120_][i_121_][i]
									= (byte) 50;
							this.aByteArrayArrayArray134[i_120_][i_121_ + 1][i]
									= (byte) 50;
						}
						if (class46.aBoolean764) {
							this.anIntArrayArrayArray135[i_120_][i_121_][i]
									|= 0x492;
						}
					}
					if (class46.aBoolean767 && (class11 != null)) {
						class11.method211(i, i_123_, i_121_, i_119_, (byte) 1,
								class46.aBoolean757);
					}
					if (class46.anInt775 != 16) {
						class25.method290(i, 441, class46.anInt775, i_121_,
								i_120_);
					}
				} else if (i_119_ == 1) {
					Class30_Sub2_Sub4 class30_sub2_sub4;
					if ((class46.anInt781 == -1)
							&& (class46.anIntArray759 == null)) {
						class30_sub2_sub4
						= class46.method578(1, i_123_, i_124_, i_125_,
								i_126_, i_127_, -1);
					} else {
						class30_sub2_sub4
						= new Class30_Sub2_Sub4_Sub5(i_122_, i_123_, 1,
								i_125_, (byte) 7,
								i_126_, i_124_,
								i_127_,
								class46.anInt781,
								true);
					}
					class25.method282(MapLoader.anIntArray140[i_123_], class30_sub2_sub4,
							true, i_129_, i, i_130_, i_121_, null,
							i_128_, 0, i_120_);
					if (class46.aBoolean779) {
						if (i_123_ == 0) {
							this.aByteArrayArrayArray134[i_120_][i_121_][i + 1]
									= (byte) 50;
						} else if (i_123_ == 1) {
							this.aByteArrayArrayArray134[i_120_][i_121_ + 1][i + 1]
									= (byte) 50;
						} else if (i_123_ == 2) {
							this.aByteArrayArrayArray134[i_120_][i_121_ + 1][i]
									= (byte) 50;
						} else if (i_123_ == 3) {
							this.aByteArrayArrayArray134[i_120_][i_121_][i]
									= (byte) 50;
						}
					}
					if (class46.aBoolean767 && (class11 != null)) {
						class11.method211(i, i_123_, i_121_, i_119_, (byte) 1,
								class46.aBoolean757);
					}
				} else if (i_119_ == 2) {
					final int i_137_ = (i_123_ + 1) & 0x3;
					Class30_Sub2_Sub4 class30_sub2_sub4;
					Class30_Sub2_Sub4 class30_sub2_sub4_138_;
					if ((class46.anInt781 == -1)
							&& (class46.anIntArray759 == null)) {
						class30_sub2_sub4
						= class46.method578(2, 4 + i_123_, i_124_, i_125_,
								i_126_, i_127_, -1);
						class30_sub2_sub4_138_
						= class46.method578(2, i_137_, i_124_, i_125_,
								i_126_, i_127_, -1);
					} else {
						class30_sub2_sub4
						= new Class30_Sub2_Sub4_Sub5(i_122_, 4 + i_123_, 2,
								i_125_, (byte) 7,
								i_126_, i_124_,
								i_127_,
								class46.anInt781,
								true);
						class30_sub2_sub4_138_
						= new Class30_Sub2_Sub4_Sub5(i_122_, i_137_, 2,
								i_125_, (byte) 7,
								i_126_, i_124_,
								i_127_,
								class46.anInt781,
								true);
					}
					class25.method282(MapLoader.anIntArray152[i_123_], class30_sub2_sub4,
							true, i_129_, i, i_130_, i_121_,
							class30_sub2_sub4_138_, i_128_,
							MapLoader.anIntArray152[i_137_], i_120_);
					if (class46.aBoolean764) {
						if (i_123_ == 0) {
							this.anIntArrayArrayArray135[i_120_][i_121_][i]
									|= 0x249;
							this.anIntArrayArrayArray135[i_120_][i_121_][i + 1]
									|= 0x492;
						} else if (i_123_ == 1) {
							this.anIntArrayArrayArray135[i_120_][i_121_][i + 1]
									|= 0x492;
							this.anIntArrayArrayArray135[i_120_][i_121_ + 1][i]
									|= 0x249;
						} else if (i_123_ == 2) {
							this.anIntArrayArrayArray135[i_120_][i_121_ + 1][i]
									|= 0x249;
							this.anIntArrayArrayArray135[i_120_][i_121_][i]
									|= 0x492;
						} else if (i_123_ == 3) {
							this.anIntArrayArrayArray135[i_120_][i_121_][i]
									|= 0x492;
							this.anIntArrayArrayArray135[i_120_][i_121_][i]
									|= 0x249;
						}
					}
					if (class46.aBoolean767 && (class11 != null)) {
						class11.method211(i, i_123_, i_121_, i_119_, (byte) 1,
								class46.aBoolean757);
					}
					if (class46.anInt775 != 16) {
						class25.method290(i, 441, class46.anInt775, i_121_,
								i_120_);
					}
				} else if (i_119_ == 3) {
					Class30_Sub2_Sub4 class30_sub2_sub4;
					if ((class46.anInt781 == -1)
							&& (class46.anIntArray759 == null)) {
						class30_sub2_sub4
						= class46.method578(3, i_123_, i_124_, i_125_,
								i_126_, i_127_, -1);
					} else {
						class30_sub2_sub4
						= new Class30_Sub2_Sub4_Sub5(i_122_, i_123_, 3,
								i_125_, (byte) 7,
								i_126_, i_124_,
								i_127_,
								class46.anInt781,
								true);
					}
					class25.method282(MapLoader.anIntArray140[i_123_], class30_sub2_sub4,
							true, i_129_, i, i_130_, i_121_, null,
							i_128_, 0, i_120_);
					if (class46.aBoolean779) {
						if (i_123_ == 0) {
							this.aByteArrayArrayArray134[i_120_][i_121_][i + 1]
									= (byte) 50;
						} else if (i_123_ == 1) {
							this.aByteArrayArrayArray134[i_120_][i_121_ + 1][i + 1]
									= (byte) 50;
						} else if (i_123_ == 2) {
							this.aByteArrayArrayArray134[i_120_][i_121_ + 1][i]
									= (byte) 50;
						} else if (i_123_ == 3) {
							this.aByteArrayArrayArray134[i_120_][i_121_][i]
									= (byte) 50;
						}
					}
					if (class46.aBoolean767 && (class11 != null)) {
						class11.method211(i, i_123_, i_121_, i_119_, (byte) 1,
								class46.aBoolean757);
					}
				} else if (i_119_ == 9) {
					Class30_Sub2_Sub4 class30_sub2_sub4;
					if ((class46.anInt781 == -1)
							&& (class46.anIntArray759 == null)) {
						class30_sub2_sub4
						= class46.method578(i_119_, i_123_, i_124_, i_125_,
								i_126_, i_127_, -1);
					} else {
						class30_sub2_sub4
						= new Class30_Sub2_Sub4_Sub5(i_122_, i_123_,
								i_119_, i_125_,
								(byte) 7, i_126_,
								i_124_, i_127_,
								class46.anInt781,
								true);
					}
					class25.method284(i_129_, i_130_, i_128_, 1,
							class30_sub2_sub4, 1, i_120_, 0,
							(byte) 110, i, i_121_);
					if (class46.aBoolean767 && (class11 != null)) {
						class11.method212(class46.aBoolean757, MapLoader.anInt138,
								class46.anInt744, class46.anInt761,
								i_121_, i, i_123_);
					}
				} else {
					if (class46.aBoolean762) {
						if (i_123_ == 1) {
							final int i_139_ = i_127_;
							i_127_ = i_126_;
							i_126_ = i_125_;
							i_125_ = i_124_;
							i_124_ = i_139_;
						} else if (i_123_ == 2) {
							int i_140_ = i_127_;
							i_127_ = i_125_;
							i_125_ = i_140_;
							i_140_ = i_126_;
							i_126_ = i_124_;
							i_124_ = i_140_;
						} else if (i_123_ == 3) {
							final int i_141_ = i_127_;
							i_127_ = i_124_;
							i_124_ = i_125_;
							i_125_ = i_126_;
							i_126_ = i_141_;
						}
					}
					if (i_119_ == 4) {
						Class30_Sub2_Sub4 class30_sub2_sub4;
						if ((class46.anInt781 == -1)
								&& (class46.anIntArray759 == null)) {
							class30_sub2_sub4
							= class46.method578(4, 0, i_124_, i_125_,
									i_126_, i_127_, -1);
						} else {
							class30_sub2_sub4
							= new Class30_Sub2_Sub4_Sub5(i_122_, 0, 4,
									i_125_, (byte) 7,
									i_126_, i_124_,
									i_127_,
									class46.anInt781,
									true);
						}
						class25.method283(i_129_, i, i_123_ * 512, -460,
								i_120_, 0, i_128_, class30_sub2_sub4,
								i_121_, i_130_, 0,
								MapLoader.anIntArray152[i_123_]);
					} else if (i_119_ == 5) {
						int i_142_ = 16;
						final int i_143_ = class25.method300(i_120_, i_121_, i);
						if (i_143_ > 0) {
							i_142_ = (Class46.method572((i_143_ >> 14) & 0x7fff)
									.anInt775);
						}
						Class30_Sub2_Sub4 class30_sub2_sub4;
						if ((class46.anInt781 == -1)
								&& (class46.anIntArray759 == null)) {
							class30_sub2_sub4
							= class46.method578(4, 0, i_124_, i_125_,
									i_126_, i_127_, -1);
						} else {
							class30_sub2_sub4
							= new Class30_Sub2_Sub4_Sub5(i_122_, 0, 4,
									i_125_, (byte) 7,
									i_126_, i_124_,
									i_127_,
									class46.anInt781,
									true);
						}
						class25.method283(i_129_, i, i_123_ * 512, -460,
								i_120_,
								MapLoader.anIntArray137[i_123_] * i_142_,
								i_128_, class30_sub2_sub4, i_121_,
								i_130_,
								MapLoader.anIntArray144[i_123_] * i_142_,
								MapLoader.anIntArray152[i_123_]);
					} else if (i_119_ == 6) {
						Class30_Sub2_Sub4 class30_sub2_sub4;
						if ((class46.anInt781 == -1)
								&& (class46.anIntArray759 == null)) {
							class30_sub2_sub4
							= class46.method578(4, 0, i_124_, i_125_,
									i_126_, i_127_, -1);
						} else {
							class30_sub2_sub4
							= new Class30_Sub2_Sub4_Sub5(i_122_, 0, 4,
									i_125_, (byte) 7,
									i_126_, i_124_,
									i_127_,
									class46.anInt781,
									true);
						}
						class25.method283(i_129_, i, i_123_, -460, i_120_, 0,
								i_128_, class30_sub2_sub4, i_121_,
								i_130_, 0, 256);
					} else if (i_119_ == 7) {
						Class30_Sub2_Sub4 class30_sub2_sub4;
						if ((class46.anInt781 == -1)
								&& (class46.anIntArray759 == null)) {
							class30_sub2_sub4
							= class46.method578(4, 0, i_124_, i_125_,
									i_126_, i_127_, -1);
						} else {
							class30_sub2_sub4
							= new Class30_Sub2_Sub4_Sub5(i_122_, 0, 4,
									i_125_, (byte) 7,
									i_126_, i_124_,
									i_127_,
									class46.anInt781,
									true);
						}
						class25.method283(i_129_, i, i_123_, -460, i_120_, 0,
								i_128_, class30_sub2_sub4, i_121_,
								i_130_, 0, 512);
					} else if (i_119_ == 8) {
						Class30_Sub2_Sub4 class30_sub2_sub4;
						if ((class46.anInt781 == -1)
								&& (class46.anIntArray759 == null)) {
							class30_sub2_sub4
							= class46.method578(4, 0, i_124_, i_125_,
									i_126_, i_127_, -1);
						} else {
							class30_sub2_sub4
							= new Class30_Sub2_Sub4_Sub5(i_122_, 0, 4,
									i_125_, (byte) 7,
									i_126_, i_124_,
									i_127_,
									class46.anInt781,
									true);
						}
						class25.method283(i_129_, i, i_123_, -460, i_120_, 0,
								i_128_, class30_sub2_sub4, i_121_,
								i_130_, 0, 768);
					}
				}
			}
		}
	}

	private static final int method176(final int i, final int i_144_, final int i_145_) {
		final int i_146_ = i / i_145_;
		final int i_147_ = i & (i_145_ - 1);
		final int i_148_ = i_144_ / i_145_;
		final int i_149_ = i_144_ & (i_145_ - 1);
		final int i_150_ = MapLoader.method186(i_146_, i_148_);
		final int i_151_ = MapLoader.method186(i_146_ + 1, i_148_);
		final int i_152_ = MapLoader.method186(i_146_, i_148_ + 1);
		final int i_153_ = MapLoader.method186(i_146_ + 1, i_148_ + 1);
		final int i_154_ = MapLoader.method184(i_150_, i_151_, i_147_, i_145_);
		final int i_155_ = MapLoader.method184(i_152_, i_153_, i_147_, i_145_);
		return MapLoader.method184(i_154_, i_155_, i_149_, i_145_);
	}

	private final int method177(final int i, int i_156_, final int i_157_) {
		if (i_157_ > 179) {
			i_156_ /= 2;
		}
		if (i_157_ > 192) {
			i_156_ /= 2;
		}
		if (i_157_ > 217) {
			i_156_ /= 2;
		}
		if (i_157_ > 243) {
			i_156_ /= 2;
		}
		final int i_158_ = ((i / 4) << 10) + ((i_156_ / 32) << 7) + (i_157_ / 2);
		return i_158_;
	}

	public static final boolean method178(final int i, int i_159_, final int i_160_) {
		final Class46 class46 = Class46.method572(i);
		if (i_160_ != 8) {
			for (int i_161_ = 1; i_161_ > 0; i_161_++) {
				/* empty */
			}
		}
		if (i_159_ == 11) {
			i_159_ = 10;
		}
		if ((i_159_ >= 5) && (i_159_ <= 8)) {
			i_159_ = 4;
		}
		return class46.method577(i_159_, true);
	}

	public final void method179(final int i, final int i_162_, final Class11[] class11s,
			final int i_163_, final int i_164_, final int i_165_, final byte[] is,
			final int i_166_, final int i_167_, final int i_168_) {
		for (int i_169_ = 0; i_169_ < 8; i_169_++) {
			for (int i_170_ = 0; i_170_ < 8; i_170_++) {
				if (((i_164_ + i_169_) > 0) && ((i_164_ + i_169_) < 103)
						&& ((i_168_ + i_170_) > 0) && ((i_168_ + i_170_) < 103)) {
					class11s[i_167_].anIntArrayArray294[i_164_ + i_169_]
							[i_168_ + i_170_]
									&= ~0x1000000;
				}
			}
		}
		if ((i_163_ < 9) || (i_163_ > 9)) {
			for (int i_171_ = 1; i_171_ > 0; i_171_++) {
				/* empty */
			}
		}
		final Class30_Sub2_Sub2 class30_sub2_sub2 = new Class30_Sub2_Sub2(is, 891);
		for (int i_172_ = 0; i_172_ < 4; i_172_++) {
			for (int i_173_ = 0; i_173_ < 64; i_173_++) {
				for (int i_174_ = 0; i_174_ < 64; i_174_++) {
					if ((i_172_ == i) && (i_173_ >= i_165_) && (i_173_ < (i_165_ + 8))
							&& (i_174_ >= i_166_) && (i_174_ < (i_166_ + 8))) {
						this.method181(i_168_ + Class4.method156(i_174_ & 0x7,
								i_162_, -383,
								i_173_ & 0x7),
								0, class30_sub2_sub2,
								(i_164_
										+ Class4.method155(i_162_, i_174_ & 0x7,
												i_173_ & 0x7, false)),
												i_167_, i_162_, 942, 0);
					} else {
						this.method181(-1, 0, class30_sub2_sub2, -1, 0, 0, 942, 0);
					}
				}
			}
		}
	}

	public final void method180(final byte[] is, final int i, final int i_175_, final int i_176_,
			final int i_177_, final byte i_178_, final Class11[] class11s) {
		for (int i_179_ = 0; i_179_ < 4; i_179_++) {
			for (int i_180_ = 0; i_180_ < 64; i_180_++) {
				for (int i_181_ = 0; i_181_ < 64; i_181_++) {
					if (((i_175_ + i_180_) > 0) && ((i_175_ + i_180_) < 103)
							&& ((i + i_181_) > 0) && ((i + i_181_) < 103)) {
						class11s[i_179_].anIntArrayArray294
						[i_175_ + i_180_][i + i_181_]
								&= ~0x1000000;
					}
				}
			}
		}
		final Class30_Sub2_Sub2 class30_sub2_sub2 = new Class30_Sub2_Sub2(is, 891);
		for (int i_182_ = 0; i_182_ < 4; i_182_++) {
			for (int i_183_ = 0; i_183_ < 64; i_183_++) {
				for (int i_184_ = 0; i_184_ < 64; i_184_++) {
					this.method181(i_184_ + i, i_177_, class30_sub2_sub2,
							i_183_ + i_175_, i_182_, 0, 942, i_176_);
				}
			}
		}
		if (i_178_ != 4) {
			this.aBoolean143 = !this.aBoolean143;
		}
	}

	private final void method181
	(final int i, final int i_185_, final Class30_Sub2_Sub2 class30_sub2_sub2, final int i_186_,
			final int i_187_, final int i_188_, int i_189_, final int i_190_) {
		i_189_ = 36 / i_189_;
		if ((i_186_ >= 0) && (i_186_ < 104) && (i >= 0) && (i < 104)) {
			this.aByteArrayArrayArray149[i_187_][i_186_][i] = (byte) 0;
			for (;;) {
				final int i_191_ = class30_sub2_sub2.method408();
				if (i_191_ == 0) {
					if (i_187_ == 0) {
						this.anIntArrayArrayArray129[0][i_186_][i]
								= -MapLoader.method172(932731 + i_186_ + i_190_,
										556238 + i + i_185_) * 8;
					} else {
						this.anIntArrayArrayArray129[i_187_][i_186_][i]
								= (this.anIntArrayArrayArray129[i_187_ - 1][i_186_][i]
										- 240);
						break;
					}
					break;
				}
				if (i_191_ == 1) {
					int i_192_ = class30_sub2_sub2.method408();
					if (i_192_ == 1) {
						i_192_ = 0;
					}
					if (i_187_ == 0) {
						this.anIntArrayArrayArray129[0][i_186_][i] = -i_192_ * 8;
					} else {
						this.anIntArrayArrayArray129[i_187_][i_186_][i]
								= (this.anIntArrayArrayArray129[i_187_ - 1][i_186_][i]
										- (i_192_ * 8));
						break;
					}
					break;
				}
				if (i_191_ <= 49) {
					this.aByteArrayArrayArray130[i_187_][i_186_][i]
							= class30_sub2_sub2.method409();
					this.aByteArrayArrayArray136[i_187_][i_186_][i]
							= (byte) ((i_191_ - 2) / 4);
					this.aByteArrayArrayArray148[i_187_][i_186_][i]
							= (byte) (((i_191_ - 2) + i_188_) & 0x3);
				} else if (i_191_ <= 81) {
					this.aByteArrayArrayArray149[i_187_][i_186_][i]
							= (byte) (i_191_ - 49);
				} else {
					this.aByteArrayArrayArray142[i_187_][i_186_][i]
							= (byte) (i_191_ - 81);
				}
			}
		} else {
			for (;;) {
				final int i_193_ = class30_sub2_sub2.method408();
				if (i_193_ == 0) {
					break;
				}
				if (i_193_ == 1) {
					class30_sub2_sub2.method408();
					break;
				}
				if (i_193_ <= 49) {
					class30_sub2_sub2.method408();
				}
			}
		}
	}

	public int method182(final int i, final int i_194_, final int i_195_, final int i_196_) {
		if (i_196_ != 0) {
			return 2;
		}
		if ((this.aByteArrayArrayArray149[i_194_][i_195_][i] & 0x8) != 0) {
			return 0;
		}
		if ((i_194_ > 0) && ((this.aByteArrayArrayArray149[1][i_195_][i] & 0x2) != 0)) {
			return i_194_ - 1;
		}
		return i_194_;
	}

	public final void method183(final Class11[] class11s, final Class25 class25, final int i,
			final int i_197_, final int i_198_, final boolean bool,
			final int i_199_, final byte[] is, final int i_200_, final int i_201_,
			final int i_202_) {
		final Class30_Sub2_Sub2 class30_sub2_sub2 = new Class30_Sub2_Sub2(is, 891);
		int i_203_ = -1;
		if (!bool) {
			this.aBoolean143 = !this.aBoolean143;
		}
		for (;;) {
			final int i_204_ = class30_sub2_sub2.method422();
			if (i_204_ == 0) {
				break;
			}
			i_203_ += i_204_;
			int i_205_ = 0;
			for (;;) {
				final int i_206_ = class30_sub2_sub2.method422();
				if (i_206_ == 0) {
					break;
				}
				i_205_ += i_206_ - 1;
				final int i_207_ = i_205_ & 0x3f;
				final int i_208_ = (i_205_ >> 6) & 0x3f;
				final int i_209_ = i_205_ >> 12;
		final int i_210_ = class30_sub2_sub2.method408();
		final int i_211_ = i_210_ >> 2;
		final int i_212_ = i_210_ & 0x3;
		if ((i_209_ == i) && (i_208_ >= i_200_) && (i_208_ < (i_200_ + 8))
				&& (i_207_ >= i_198_) && (i_207_ < (i_198_ + 8))) {
			final Class46 class46 = Class46.method572(i_203_);
			final int i_213_
			= (i_197_
					+ Class4.method157(i_201_, class46.anInt761,
							i_208_ & 0x7, (byte) 113,
							i_207_ & 0x7, class46.anInt744));
			final int i_214_
			= (i_202_
					+ Class4.method158(-433, i_207_ & 0x7,
							class46.anInt761, i_201_,
							class46.anInt744, i_208_ & 0x7));
			if ((i_213_ > 0) && (i_214_ > 0) && (i_213_ < 103)
					&& (i_214_ < 103)) {
				int i_215_ = i_209_;
				if ((this.aByteArrayArrayArray149[1][i_213_][i_214_] & 0x2)
						== 2) {
					i_215_--;
				}
				Class11 class11 = null;
				if (i_215_ >= 0) {
					class11 = class11s[i_215_];
				}
				this.method175(i_214_, class25, class11, i_211_, i_199_,
						i_213_, i_203_, false,
						(i_212_ + i_201_) & 0x3);
			}
		}
			}
		}
	}

	private static final int method184(final int i, final int i_216_, final int i_217_,
			final int i_218_) {
		final int i_219_ = ((65536 - (Class30_Sub2_Sub1_Sub3.anIntArray1471
				[(i_217_ * 1024) / i_218_]))
				>> 1);
		return ((i * (65536 - i_219_)) >> 16) + ((i_216_ * i_219_) >> 16);
	}

	private final int method185(final int i, int i_220_) {
		if (i == -2) {
			return 12345678;
		}
		if (i == -1) {
			if (i_220_ < 0) {
				i_220_ = 0;
			} else if (i_220_ > 127) {
				i_220_ = 127;
			}
			i_220_ = 127 - i_220_;
			return i_220_;
		}
		i_220_ = (i_220_ * (i & 0x7f)) / 128;
		if (i_220_ < 2) {
			i_220_ = 2;
		} else if (i_220_ > 126) {
			i_220_ = 126;
		}
		return (i & 0xff80) + i_220_;
	}

	private static final int method186(final int i, final int i_221_) {
		final int i_222_
		= (MapLoader.method170(i - 1, i_221_ - 1) + MapLoader.method170(i + 1, i_221_ - 1)
				+ MapLoader.method170(i - 1, i_221_ + 1) + MapLoader.method170(i + 1, i_221_ + 1));
		final int i_223_ = (MapLoader.method170(i - 1, i_221_) + MapLoader.method170(i + 1, i_221_)
				+ MapLoader.method170(i, i_221_ - 1) + MapLoader.method170(i, i_221_ + 1));
		final int i_224_ = MapLoader.method170(i, i_221_);
		return (i_222_ / 16) + (i_223_ / 8) + (i_224_ / 4);
	}

	private static final int method187(final int i, int i_225_) {
		if (i == -1) {
			return 12345678;
		}
		i_225_ = (i_225_ * (i & 0x7f)) / 128;
		if (i_225_ < 2) {
			i_225_ = 2;
		} else if (i_225_ > 126) {
			i_225_ = 126;
		}
		return (i & 0xff80) + i_225_;
	}

	public static final void method188(final Class25 class25, final int i, final int i_226_,
			final int i_227_, final int i_228_, final Class11 class11,
			final int[][][] is, final int i_229_, final int i_230_,
			final int i_231_, final byte i_232_) {
		int i_233_ = is[i_228_][i_229_][i_226_];
		int i_234_ = is[i_228_][i_229_ + 1][i_226_];
		int i_235_ = is[i_228_][i_229_ + 1][i_226_ + 1];
		int i_236_ = is[i_228_][i_229_][i_226_ + 1];
		if (i_232_ != 93) {
		}
		final int i_237_ = (i_233_ + i_234_ + i_235_ + i_236_) >> 2;
		final Class46 class46 = Class46.method572(i_230_);
		int i_238_ = i_229_ + (i_226_ << 7) + (i_230_ << 14) + 1073741824;
		if (!class46.aBoolean778) {
			i_238_ += -2147483648;
		}
		final byte i_239_ = (byte) ((i << 6) + i_227_);
		if (i_227_ == 22) {
			Class30_Sub2_Sub4 class30_sub2_sub4;
			if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
				class30_sub2_sub4 = class46.method578(22, i, i_233_, i_234_,
						i_235_, i_236_, -1);
			} else {
				class30_sub2_sub4
				= new Class30_Sub2_Sub4_Sub5(i_230_, i, 22, i_234_,
						(byte) 7, i_235_, i_233_,
						i_236_, class46.anInt781,
						true);
			}
			class25.method280(i_231_, i_237_, i_226_, 68, class30_sub2_sub4,
					i_239_, i_238_, i_229_);
			if (class46.aBoolean767 && class46.aBoolean778) {
				class11.method213(i_226_, 0, i_229_);
			}
		} else if ((i_227_ == 10) || (i_227_ == 11)) {
			Class30_Sub2_Sub4 class30_sub2_sub4;
			if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
				class30_sub2_sub4 = class46.method578(10, i, i_233_, i_234_,
						i_235_, i_236_, -1);
			} else {
				class30_sub2_sub4
				= new Class30_Sub2_Sub4_Sub5(i_230_, i, 10, i_234_,
						(byte) 7, i_235_, i_233_,
						i_236_, class46.anInt781,
						true);
			}
			if (class30_sub2_sub4 != null) {
				int i_240_ = 0;
				if (i_227_ == 11) {
					i_240_ += 256;
				}
				int i_241_;
				int i_242_;
				if ((i == 1) || (i == 3)) {
					i_241_ = class46.anInt761;
					i_242_ = class46.anInt744;
				} else {
					i_241_ = class46.anInt744;
					i_242_ = class46.anInt761;
				}
				class25.method284(i_238_, i_239_, i_237_, i_242_,
						class30_sub2_sub4, i_241_, i_231_, i_240_,
						(byte) 110, i_226_, i_229_);
			}
			if (class46.aBoolean767) {
				class11.method212(class46.aBoolean757, MapLoader.anInt138,
						class46.anInt744, class46.anInt761, i_229_,
						i_226_, i);
			}
		} else if (i_227_ >= 12) {
			Class30_Sub2_Sub4 class30_sub2_sub4;
			if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
				class30_sub2_sub4
				= class46.method578(i_227_, i, i_233_, i_234_, i_235_,
						i_236_, -1);
			} else {
				class30_sub2_sub4
				= new Class30_Sub2_Sub4_Sub5(i_230_, i, i_227_, i_234_,
						(byte) 7, i_235_, i_233_,
						i_236_, class46.anInt781,
						true);
			}
			class25.method284(i_238_, i_239_, i_237_, 1, class30_sub2_sub4, 1,
					i_231_, 0, (byte) 110, i_226_, i_229_);
			if (class46.aBoolean767) {
				class11.method212(class46.aBoolean757, MapLoader.anInt138,
						class46.anInt744, class46.anInt761, i_229_,
						i_226_, i);
			}
		} else if (i_227_ == 0) {
			Class30_Sub2_Sub4 class30_sub2_sub4;
			if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
				class30_sub2_sub4 = class46.method578(0, i, i_233_, i_234_,
						i_235_, i_236_, -1);
			} else {
				class30_sub2_sub4
				= new Class30_Sub2_Sub4_Sub5(i_230_, i, 0, i_234_,
						(byte) 7, i_235_, i_233_,
						i_236_, class46.anInt781,
						true);
			}
			class25.method282(MapLoader.anIntArray152[i], class30_sub2_sub4, true,
					i_238_, i_226_, i_239_, i_229_, null, i_237_, 0,
					i_231_);
			if (class46.aBoolean767) {
				class11.method211(i_226_, i, i_229_, i_227_, (byte) 1,
						class46.aBoolean757);
			}
		} else if (i_227_ == 1) {
			Class30_Sub2_Sub4 class30_sub2_sub4;
			if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
				class30_sub2_sub4 = class46.method578(1, i, i_233_, i_234_,
						i_235_, i_236_, -1);
			} else {
				class30_sub2_sub4
				= new Class30_Sub2_Sub4_Sub5(i_230_, i, 1, i_234_,
						(byte) 7, i_235_, i_233_,
						i_236_, class46.anInt781,
						true);
			}
			class25.method282(MapLoader.anIntArray140[i], class30_sub2_sub4, true,
					i_238_, i_226_, i_239_, i_229_, null, i_237_, 0,
					i_231_);
			if (class46.aBoolean767) {
				class11.method211(i_226_, i, i_229_, i_227_, (byte) 1,
						class46.aBoolean757);
			}
		} else if (i_227_ == 2) {
			final int i_243_ = (i + 1) & 0x3;
			Class30_Sub2_Sub4 class30_sub2_sub4;
			Class30_Sub2_Sub4 class30_sub2_sub4_244_;
			if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
				class30_sub2_sub4 = class46.method578(2, 4 + i, i_233_, i_234_,
						i_235_, i_236_, -1);
				class30_sub2_sub4_244_
				= class46.method578(2, i_243_, i_233_, i_234_, i_235_,
						i_236_, -1);
			} else {
				class30_sub2_sub4
				= new Class30_Sub2_Sub4_Sub5(i_230_, 4 + i, 2, i_234_,
						(byte) 7, i_235_, i_233_,
						i_236_, class46.anInt781,
						true);
				class30_sub2_sub4_244_
				= new Class30_Sub2_Sub4_Sub5(i_230_, i_243_, 2, i_234_,
						(byte) 7, i_235_, i_233_,
						i_236_, class46.anInt781,
						true);
			}
			class25.method282(MapLoader.anIntArray152[i], class30_sub2_sub4, true,
					i_238_, i_226_, i_239_, i_229_,
					class30_sub2_sub4_244_, i_237_,
					MapLoader.anIntArray152[i_243_], i_231_);
			if (class46.aBoolean767) {
				class11.method211(i_226_, i, i_229_, i_227_, (byte) 1,
						class46.aBoolean757);
			}
		} else if (i_227_ == 3) {
			Class30_Sub2_Sub4 class30_sub2_sub4;
			if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
				class30_sub2_sub4 = class46.method578(3, i, i_233_, i_234_,
						i_235_, i_236_, -1);
			} else {
				class30_sub2_sub4
				= new Class30_Sub2_Sub4_Sub5(i_230_, i, 3, i_234_,
						(byte) 7, i_235_, i_233_,
						i_236_, class46.anInt781,
						true);
			}
			class25.method282(MapLoader.anIntArray140[i], class30_sub2_sub4, true,
					i_238_, i_226_, i_239_, i_229_, null, i_237_, 0,
					i_231_);
			if (class46.aBoolean767) {
				class11.method211(i_226_, i, i_229_, i_227_, (byte) 1,
						class46.aBoolean757);
			}
		} else if (i_227_ == 9) {
			Class30_Sub2_Sub4 class30_sub2_sub4;
			if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
				class30_sub2_sub4
				= class46.method578(i_227_, i, i_233_, i_234_, i_235_,
						i_236_, -1);
			} else {
				class30_sub2_sub4
				= new Class30_Sub2_Sub4_Sub5(i_230_, i, i_227_, i_234_,
						(byte) 7, i_235_, i_233_,
						i_236_, class46.anInt781,
						true);
			}
			class25.method284(i_238_, i_239_, i_237_, 1, class30_sub2_sub4, 1,
					i_231_, 0, (byte) 110, i_226_, i_229_);
			if (class46.aBoolean767) {
				class11.method212(class46.aBoolean757, MapLoader.anInt138,
						class46.anInt744, class46.anInt761, i_229_,
						i_226_, i);
			}
		} else {
			if (class46.aBoolean762) {
				if (i == 1) {
					final int i_245_ = i_236_;
					i_236_ = i_235_;
					i_235_ = i_234_;
					i_234_ = i_233_;
					i_233_ = i_245_;
				} else if (i == 2) {
					int i_246_ = i_236_;
					i_236_ = i_234_;
					i_234_ = i_246_;
					i_246_ = i_235_;
					i_235_ = i_233_;
					i_233_ = i_246_;
				} else if (i == 3) {
					final int i_247_ = i_236_;
					i_236_ = i_233_;
					i_233_ = i_234_;
					i_234_ = i_235_;
					i_235_ = i_247_;
				}
			}
			if (i_227_ == 4) {
				Class30_Sub2_Sub4 class30_sub2_sub4;
				if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
					class30_sub2_sub4 = class46.method578(4, 0, i_233_, i_234_,
							i_235_, i_236_, -1);
				} else {
					class30_sub2_sub4
					= new Class30_Sub2_Sub4_Sub5(i_230_, 0, 4, i_234_,
							(byte) 7, i_235_, i_233_,
							i_236_, class46.anInt781,
							true);
				}
				class25.method283(i_238_, i_226_, i * 512, -460, i_231_, 0,
						i_237_, class30_sub2_sub4, i_229_, i_239_, 0,
						MapLoader.anIntArray152[i]);
			} else if (i_227_ == 5) {
				int i_248_ = 16;
				final int i_249_ = class25.method300(i_231_, i_229_, i_226_);
				if (i_249_ > 0) {
					i_248_ = Class46.method572((i_249_ >> 14) & 0x7fff).anInt775;
				}
				Class30_Sub2_Sub4 class30_sub2_sub4;
				if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
					class30_sub2_sub4 = class46.method578(4, 0, i_233_, i_234_,
							i_235_, i_236_, -1);
				} else {
					class30_sub2_sub4
					= new Class30_Sub2_Sub4_Sub5(i_230_, 0, 4, i_234_,
							(byte) 7, i_235_, i_233_,
							i_236_, class46.anInt781,
							true);
				}
				class25.method283(i_238_, i_226_, i * 512, -460, i_231_,
						MapLoader.anIntArray137[i] * i_248_, i_237_,
						class30_sub2_sub4, i_229_, i_239_,
						MapLoader.anIntArray144[i] * i_248_, MapLoader.anIntArray152[i]);
			} else if (i_227_ == 6) {
				Class30_Sub2_Sub4 class30_sub2_sub4;
				if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
					class30_sub2_sub4 = class46.method578(4, 0, i_233_, i_234_,
							i_235_, i_236_, -1);
				} else {
					class30_sub2_sub4
					= new Class30_Sub2_Sub4_Sub5(i_230_, 0, 4, i_234_,
							(byte) 7, i_235_, i_233_,
							i_236_, class46.anInt781,
							true);
				}
				class25.method283(i_238_, i_226_, i, -460, i_231_, 0, i_237_,
						class30_sub2_sub4, i_229_, i_239_, 0, 256);
			} else if (i_227_ == 7) {
				Class30_Sub2_Sub4 class30_sub2_sub4;
				if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
					class30_sub2_sub4 = class46.method578(4, 0, i_233_, i_234_,
							i_235_, i_236_, -1);
				} else {
					class30_sub2_sub4
					= new Class30_Sub2_Sub4_Sub5(i_230_, 0, 4, i_234_,
							(byte) 7, i_235_, i_233_,
							i_236_, class46.anInt781,
							true);
				}
				class25.method283(i_238_, i_226_, i, -460, i_231_, 0, i_237_,
						class30_sub2_sub4, i_229_, i_239_, 0, 512);
			} else if (i_227_ == 8) {
				Class30_Sub2_Sub4 class30_sub2_sub4;
				if ((class46.anInt781 == -1) && (class46.anIntArray759 == null)) {
					class30_sub2_sub4 = class46.method578(4, 0, i_233_, i_234_,
							i_235_, i_236_, -1);
				} else {
					class30_sub2_sub4
					= new Class30_Sub2_Sub4_Sub5(i_230_, 0, 4, i_234_,
							(byte) 7, i_235_, i_233_,
							i_236_, class46.anInt781,
							true);
				}
				class25.method283(i_238_, i_226_, i, -460, i_231_, 0, i_237_,
						class30_sub2_sub4, i_229_, i_239_, 0, 768);
			}
		}
	}

	public static final boolean method189(final int i, final byte[] is, final int i_250_,
			final int i_251_) {
		if ((i_251_ < 6) || (i_251_ > 6)) {
			throw new NullPointerException();
		}
		boolean bool = true;
		final Class30_Sub2_Sub2 class30_sub2_sub2 = new Class30_Sub2_Sub2(is, 891);
		int i_252_ = -1;
		for (;;) {
			final int i_253_ = class30_sub2_sub2.method422();
			if (i_253_ == 0) {
				break;
			}
			i_252_ += i_253_;
			int i_254_ = 0;
			boolean bool_255_ = false;
			for (;;) {
				if (bool_255_) {
					final int i_256_ = class30_sub2_sub2.method422();
					if (i_256_ == 0) {
						break;
					}
					class30_sub2_sub2.method408();
				} else {
					final int i_257_ = class30_sub2_sub2.method422();
					if (i_257_ == 0) {
						break;
					}
					i_254_ += i_257_ - 1;
					final int i_258_ = i_254_ & 0x3f;
					final int i_259_ = (i_254_ >> 6) & 0x3f;
					final int i_260_ = class30_sub2_sub2.method408() >> 2;
									final int i_261_ = i_259_ + i;
									final int i_262_ = i_258_ + i_250_;
									if ((i_261_ > 0) && (i_262_ > 0) && (i_261_ < 103)
											&& (i_262_ < 103)) {
										final Class46 class46 = Class46.method572(i_252_);
										if ((i_260_ != 22) || !MapLoader.aBoolean151 || class46.aBoolean778
												|| class46.aBoolean736) {
											bool &= class46.method579(true);
											bool_255_ = true;
										}
									}
				}
			}
		}
		return bool;
	}

	public final void method190(final int i, final Class11[] class11s, final int i_263_,
			final int i_264_, final Class25 class25, final byte[] is) {
		if ((i_264_ >= 7) && (i_264_ <= 7)) {
			final Class30_Sub2_Sub2 class30_sub2_sub2
			= new Class30_Sub2_Sub2(is, 891);
			int i_265_ = -1;
			for (;;) {
				final int i_266_ = class30_sub2_sub2.method422();
				if (i_266_ == 0) {
					break;
				}
				i_265_ += i_266_;
				int i_267_ = 0;
				for (;;) {
					final int i_268_ = class30_sub2_sub2.method422();
					if (i_268_ == 0) {
						break;
					}
					i_267_ += i_268_ - 1;
					final int i_269_ = i_267_ & 0x3f;
					final int i_270_ = (i_267_ >> 6) & 0x3f;
					final int i_271_ = i_267_ >> 12;
				final int i_272_ = class30_sub2_sub2.method408();
				final int i_273_ = i_272_ >> 2;
				final int i_274_ = i_272_ & 0x3;
				final int i_275_ = i_270_ + i;
				final int i_276_ = i_269_ + i_263_;
				if ((i_275_ > 0) && (i_276_ > 0) && (i_275_ < 103)
						&& (i_276_ < 103)) {
					int i_277_ = i_271_;
					if ((this.aByteArrayArrayArray149[1][i_275_][i_276_] & 0x2)
							== 2) {
						i_277_--;
					}
					Class11 class11 = null;
					if (i_277_ >= 0) {
						class11 = class11s[i_277_];
					}
					this.method175(i_276_, class25, class11, i_273_, i_271_,
							i_275_, i_265_, false, i_274_);
				}
				}
			}
		}
	}
}
