# show local coordinate of vertices

doc = MQSystem.getDocument()

curidx = doc.currentObjectIndex
if curidx != -1:
	obj = doc.object[curidx]
	if not (obj is None):
		MQSystem.println(obj.name)
		# show global inverse matrix
		mtx = doc.getGlobalInverseMatrix(obj)
		for r in range(1,5):
			MQSystem.println("|%(a) .3f %(b) .3f %(c) .3f %(d) .3f|" % {"a":mtx.get(r,1), "b":mtx.get(r,2), "c":mtx.get(r,3), "d":mtx.get(r,4)})

		# show vertices
		numVert = obj.numVertex
		for i in range(0,numVert):
			pos = obj.vertex[i].getPos()
			MQSystem.println(" v[" + `i` + "] " + str(mtx.mult(pos)))
