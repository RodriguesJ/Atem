doc = MQSystem.getDocument()
num = doc.numMaterial
for x in range(0,num):
	mat = doc.material[x]
	if mat is None: continue
	MQSystem.println(mat.name)
	MQSystem.println(" color : " + str(mat.color))
	MQSystem.println(" dif : %(#).3f" % {"#":mat.diffuse})
	MQSystem.println(" amb : %(#).3f" % {"#":mat.ambient})
	MQSystem.println(" emi : %(#).3f" % {"#":mat.emissive})
	MQSystem.println(" spc : %(#).3f" % {"#":mat.specular})
	MQSystem.println(" pow : %(#).3f" % {"#":mat.power})
	if mat.mapType == 0:
		MQSystem.println(" mapping : 0 (UV)")
	elif mat.mapType == 1:
		MQSystem.println(" mapping : 1 (flat)")
	elif mat.mapType == 2:
		MQSystem.println(" mapping : 2 (cyrindical)")
	elif mat.mapType == 3:
		MQSystem.println(" mapping : 3 (spherical)")
	if mat.mapType != 0:
		MQSystem.println(" map-scaling  : " + str(mat.mapScaling))
		MQSystem.println(" map-angle    : " + str(mat.mapAngle))
		MQSystem.println(" map-position : " + str(mat.mapPosition))
	MQSystem.println(" vertex-color : %(#)d" % {"#":mat.vertexColor})
	MQSystem.println(" shader : %(#)d (" % {"#":mat.shader} + mat.shaderName + ")")
	if mat.textureMap != "":
		MQSystem.println(" texture-map : " + mat.textureMap)
		path = mat.getTextureMapPath()
		if not (path is None):
			MQSystem.println(" texture-map-path : " + path)
	if mat.alphaMap != "":
		MQSystem.println(" alpha-map : " + mat.alphaMap)
		path = mat.getAlphaMapPath()
		if not (path is None):
			MQSystem.println(" alpha-map-path : " + path)
	if mat.bumpMap != "":
		MQSystem.println(" bump-map : " + mat.bumpMap)
		path = mat.getBumpMapPath()
		if not (path is None):
			MQSystem.println(" bump-map-path : " + path)
