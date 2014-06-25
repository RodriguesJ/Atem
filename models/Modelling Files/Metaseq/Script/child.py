doc = MQSystem.getDocument()
for parent in doc.object:
	if parent is None: continue
	newobj = MQSystem.newObject()
	doc.addObject(newobj, parent)
	break
