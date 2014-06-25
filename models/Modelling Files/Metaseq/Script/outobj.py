# example for output wavefront-OBJ file.

doc = MQSystem.getDocument()

num = doc.numObject
for n in range(0,num):
	obj = doc.object[n]
	if obj is None: continue
	numVert = obj.numVertex
	for x in range(0,numVert):
		v = "v"
		v = v + " %(#).3f" % {"#":obj.vertex[x].pos.x}
		v = v + " %(#).3f" % {"#":obj.vertex[x].pos.y}
		v = v + " %(#).3f" % {"#":obj.vertex[x].pos.z}
		MQSystem.println(v)
	numFace = obj.numFace
	for x in range(0,numFace):
		if obj.face[x].numVertex == 0: continue
		f = "f"
		for y in range(0,obj.face[x].numVertex):
			f = f + " " + `obj.face[x].index[y]+1`
		MQSystem.println(f)
