from datetime import *
from tkinter import *
from tkinter import ttk
from psycopg2 import *
import sys
from psycopg2.extensions import ISOLATION_LEVEL_AUTOCOMMIT
from re import *
from string import *
import json
from functools import *

############################################################################

global SupplierList
savefile = open("supplierlist.txt")
SupplierList = json.loads(savefile.read())

global starteritemlist
starteritemlistfile = open("starteritemlist.txt")
starteritemlist = json.loads(starteritemlistfile.read())

global mainsitemlist
mainsitemlistfile = open("mainsitemlist.txt")
mainsitemlist = json.loads(mainsitemlistfile.read())

global dessertitemlist
dessertitemlistfile = open("dessertitemlist.txt")
dessertitemlist = json.loads(dessertitemlistfile.read())

global con, cur

############################################################################

def StockControl():
	stock = Tk()
	stock.title('Stock Control')

	currentDB = ''

	############################################################################

	def createdb():

		def killerrorwindow():
			global error
			error.destroy()

		getdbname = Tk()

		global newdbname

		def setdbname():
			def createdb():
				global newdbname
				con = None
				con = connect(host = 'localhost:5433', dbname = 'lambinn', username = 'postgres', password = 'postgres')

				con.set_isolation_level(ISOLATION_LEVEL_AUTOCOMMIT)
				cur = con.cursor()
				cur.execute('ALTER USER postgres CREATEDB;')
				cur.execute('CREATE DATABASE ' + newdbname + '''CREATE TABLE Supplier
																SupplierID int4
																	PRIMARY KEY (id)
																Name 			text
																AddressLine1 	text
																AddressLine2 	text
																	default ('')
																Town 			text
																County 			text
																Postcode
																Email
																Telephone 		text
																Constraint 'Supplier already exists in Database with this ID'
																	check UNIQUE btree (id)
																Constraint 'Address Line 1 must have a value'
																	check AddressLine1 is not ''
																Constraint 'Town/City must have a value'
																	check Town is not null
																Constraint 'County must have a value'
																	check County is not null
																Constraint 'Postcode must have a value'
																	check Postcode is not null
																Constraint 'Email must have a value'
																	check Email is not null
																Constraint 'Telephone must have have a value'
																	check Telephone is not null

																CREATE TABLE Items
																ItemID 			int
																	PRIMARY KEY (id)
																ItemName 		text
																ItemDescription text
																ItemCategory 	text 
																Cost 			int4
																Constraint 'Item ID is already in the system'
																	check UNIQUE btree (id)
																										
																CREATE TABLE Stock
																ItemID 		int4
																Quantity 	int8

																CREATE TABLE Supplierquote
																SupplierID 		int4
																ProductID 		int4
																SupplierPrice 	int4

																CREATE TABLE Orderitems
																OrderID 		int4
																ProductID 		int4
																Deliverydate 	date
																Quantity 		int8				''')
				con.commit()
				cur.close()
				con.close()

				print('Database created and opened successfully.')

			global Entry0
			global newdbname
			newdbname = Entry0.get()

			if (newdbname == ""):
				global error
				error = Tk()

				Frame0 = Frame(error, borderwidth = 2)
				Frame0.grid(column = 0, row = 0)
				Label0 = Label(Frame0, text = "Database name cannot be empty!")
				Label0.grid(column = 0, row = 0)
				OkButton = Button(Frame0, text = "Ok", command = killerrorwindow)
				OkButton.grid(column = 0, row = 1)

				error.mainloop()

			else:
				createdb()
				getdbname.destroy()


		Frame0 = Frame(getdbname, borderwidth = 2)
		Frame0.grid(column = 0, row = 0)

		Label0 = Label(Frame0, text = 'Enter the name of the database to be created:')
		Label0.grid(column = 0, row = 0)

		global Entry0
		Entry0 = Entry(Frame0, width = 15, borderwidth = 3)
		Entry0.grid(column = 0, row = 1, sticky = W)

		CreateButton = Button(Frame0, text = 'CREATE', command = setdbname)
		CreateButton.grid(column = 0, row = 1, sticky = E)


	############################################################################
	global populatelistbox
	def populatelistbox():
		#contentssql = '''SELECT "Items".ItemName, "Stock".Quantity, "Supplier".Name AS SupplierName FROM "Items" INNER JOIN "Stock" ON "Stock".ItemID = "Items".ID INNER JOIN "Supplier" ON "Supplier".ID = "Stock".SupplierID ORDER BY "Items".ItemName ASC;'''
		global con, cur
		cur.execute('''SELECT "Items".ItemName, "Stock".Quantity, "Supplier".Name AS SupplierName FROM "Items" INNER JOIN "Stock" ON "Stock".ItemID = "Items".ID INNER JOIN "Supplier" ON "Supplier".ID = "Stock".SupplierID ORDER BY "Items".ItemName ASC;''')
		
		Display.delete(*Display.get_children())
		for item in cur.fetchall():
			Display.insert('', 'end', values = item)

	############################################################################

	def loaddb():
		global con
		con = None
		con = connect(host='localhost', port=5433, dbname='lambinn', user='postgres', password='postgres')

		con.set_isolation_level(ISOLATION_LEVEL_AUTOCOMMIT)

		global cur
		cur = con.cursor()

		print("Database opened successfully.")
		populatelistbox()	


	def addnewitem():
		global newitem
		newitem = Tk()

		def itemregexcheck():
			global ItemName, ItemDescription, ItemCategory, Supplier, Quantity, error

			def killregexwindow():
				global erroradditem
				erroradditem.destroy()

			def erroraddingitem(LabelText):
				global erroradditem
				erroradditem = Tk()						#Runs various Regex comparisons to check
														#data format integrity
				Frame0 = Frame(erroradditem, borderwidth = 2)
				Frame0.grid(column = 0, row = 0)

				Label0 = Label(erroradditem, text = LabelText)
				Label0.grid(column = 0, row = 1)

				OkButton = Button(erroradditem, text = 'Ok', command = killregexwindow)
				OkButton.grid(column = 0, row = 2)


			if ((ItemName == '') or (re.match(r"\s", string = ItemName)) == True):		#\s - Whitespace character
				LabelText = "Item must have a name!"	#Tabs, spaces, line break or form feed
				
				erroraddingitem(LabelText)

			elif ((re.match(r"^[0-9]*$", string = ItemName)) == True):
				LabelText = "Item's name cannot just be a number!"

				erroraddingitem(LabelText)
			
			elif ((ItemDescription == '') or (re.match(r"\s", string = ItemDescription)) == True):
				LabelText = 'Item Description cannot be empty!'

				erroraddingitem(LabelText)
			elif ((re.match(r"[0-9]", string = ItemDescription)) == True):
				LabelText = 'Item Description must not contain only numbers!'

				erroraddingitem(LabelText)

			elif ((Quantity == '') or (re.match(r"\s", string = Quantity)) == True):
				LabelText = 'Quantity must have a value!'

				erroraddingitem(LabelText)

			elif (int(Quantity) < 0):
				Labeltext = 'Quantity cannot be less than 0!'

				erroraddingitem(Labeltext)

			elif (int(Quantity) == 0):
				Labeltext = 'Quantity cannot be equal to 0!'

				erroraddingitem(Labeltext)

			else:
				error = False
				print('Item regex checks complete.')



		def submititem():
			global ItemName, ItemDescription, ItemCategory, Supplier, Quantity, error

			error = True
			ItemName = NameEntry.get()
			ItemDescription = DescEntry.get()
			ItemCategory = CategoryCombo.get()
			Supplier = SupplierCombo.get()
			Quantity = QuantityEntry.get()

			itemregexcheck()
			if (error == False):
				global SQL
				#SQL = '''INSERT INTO "Items" (Itemname, ItemDescription, ItemCategory) VALUES (%s, %s, %s); INSERT INTO "Stock" (ItemID, SupplierID, Quantity) VALUES ((SELECT ID FROM "Items" WHERE ItemName = %s), (SELECT ID FROM "Supplier" WHERE Name = %s), %s)'''


				global cur
				print('Executing SQL...')
				cur.execute('''INSERT INTO "Items" (Itemname, ItemDescription, ItemCategory) VALUES (%s, %s, %s); INSERT INTO "Stock" (ItemID, SupplierID, Quantity) VALUES ((SELECT ID FROM "Items" WHERE ItemName = %s), (SELECT ID FROM "Supplier" WHERE name = %s), %s);''', (ItemName, ItemDescription, ItemCategory, ItemName, Supplier, Quantity))
				print('SQL executed.')

				print("Writing to file...")

				global starteritemlist, mainsitemlist, dessertitemlist

				if (ItemCategory == 'Starter'):
					starteritemlist.append(ItemName)
					with open("starteritemlist.txt", "w") as outfile:
						json.dump(starteritemlist, outfile)				#Saves the new appended list to the text file
					print("Committed to file.")
				elif (ItemCategory == 'Main'):
					mainsitemlist.append(ItemName)
					with open("mainsitemlist.txt", "w") as outfile:
						json.dump(mainsitemlist, outfile)				#Saves the new appended list to the text file
					print("Committed to file.")
				elif (ItemCategory == 'Dessert'):
					dessertitemlist.append(ItemName)
					with open("dessertitemlist.txt", "w") as outfile:
						json.dump(dessertitemlist, outfile)				#Saves the new appended list to the text file
					print("Committed to file.")

				NameEntry.delete(0, 'end')
				DescEntry.delete(0, 'end')
				QuantityEntry.delete(0, 'end')

				populatelistbox()

			
		def killwindow():
			global newitem
			newitem.destroy()

		############# FRAMES ####################

		Frame0 = Frame(newitem, borderwidth = 2)
		Frame0.grid(column = 0, row = 0, columnspan = 2)

		Frame1 = Frame(newitem, borderwidth = 2)
		Frame1.grid(column = 0, row = 1)

		Frame2 = Frame(newitem, borderwidth = 2)
		Frame2.grid(column = 1, row = 1)

		Frame3 = Frame(newitem, borderwidth = 5)
		Frame3.grid(column = 1, row = 2)

		##########################################

		############## LABELS ####################

		Label0 = Label(Frame0, text = 'NEW ITEM!')
		Label0.grid(column = 0, row = 0, columnspan = 2, sticky = N)

		Label1 = Label(Frame1, text = 'Name:')
		Label1.grid(column = 0, row = 0, sticky = W)

		Label2 = Label(Frame1, text = 'Description:')
		Label2.grid(column = 0, row = 1, sticky = W)

		Label3 = Label(Frame1, text = 'Category:')
		Label3.grid(column = 0, row = 2, sticky = W)

		Label4 = Label(Frame1, text = 'Supplier:')
		Label4.grid(column = 0, row = 3, sticky = W)

		Label5 = Label(Frame1, text = 'Quantity:')
		Label5.grid(column = 0, row = 4, sticky = W)

		###########################################

		############ ENTRY BOXES ##################

		NameEntry = Entry(Frame2, width = 20)
		NameEntry.grid(column = 0, row = 0, sticky = W)

		DescEntry = Entry(Frame2, width = 20)
		DescEntry.grid(column = 0, row = 1, sticky = W)

		QuantityEntry = Entry(Frame2, width = 5)
		QuantityEntry.grid(column = 0, row = 4, sticky = W)

		############################################

		############### COMBO BOXES ################

		categories = ["Starter", "Main", "Dessert", "Salad", "Miscellaneous"]
		CategoryCombo = ttk.Combobox(Frame2, values = categories, state = 'readonly', width = 20)
		CategoryCombo.current(1) #Set selection
		CategoryCombo.grid(column = 0, row = 2)

		global SupplierList
		SupplierList = json.load(open("supplierlist.txt", "r"))
		SupplierCombo = ttk.Combobox(Frame2, values = SupplierList, state = 'readonly', width = 20)
		SupplierCombo.current(0) #Set selection
		SupplierCombo.grid(column = 0, row = 3)

		############################################

		SubmitButton = Button(Frame3, text = 'SUBMIT', command = submititem)
		SubmitButton.grid(column = 0, row = 5, sticky = E)


		newitem.mainloop()

	def newsupplier():
		global newsupplier
		newsupplier = Tk()

		def supplierregexcheck():
			global suppliername, al1, al2, suppliertown, suppliercounty, supplierpostcode, supplieremail, supplierphone

			formats = False

			def wrongformat(WrongLabelText):
				global wrongformat
				wrongformat = Tk()

				def killwindow():
					global wrongformat
					wrongformat.destroy()

				CheckFrame = Frame(wrongformat, borderwidth = 2)
				CheckFrame.grid(column = 0, row = 0)

				WrongLabel = Label(CheckFrame, text = WrongLabelText)
				WrongLabel.grid(column = 0, row = 1)

				OkBut = Button(CheckFrame, text = 'Ok', command = killwindow)
				OkBut.grid(column = 0, row = 2)

				wrongformat.mainloop()

			while (formats == False):
				if (al2 == '') or ((re.match(r"[\s]", string = al2)) == True):
					al2 = ''

				if (newsupplier.state() != 'normal'): 		#Checks to see if window is still
					formats = True 							#open to prevent infinite loop				 
				elif (suppliername == '') or ((re.match(r"[\s]", string = suppliername) == True)):
					WrongLabelText = 'Supplier Name must not be empty!'

					wrongformat(WrongLabelText)
				elif ((re.match(r"[0-9]", string = suppliername)) == True):
					WrongLabelText = 'Supplier Name cannot just be a number!'

					wrongformat(WrongLabelText)

				elif (al1 == '') or ((re.match(r"[\s]", string = al1)) == True):
					WrongLabelText = 'Address Line 1 cannot be empty!'

					wrongformat(WrongLabelText)
				elif ((re.match(r"[0-9]", string = al1)) == True):
					WrongLabelText = 'Address Line 1 cannot be just a number!'

					wrongformat(WrongLabelText)

				elif ((re.match(r"[0-9]", string = al2)) == True):
					WrongLabelText = 'Address Line 2 cannot just be a number!'

					wrongformat(WrongLabelText)

				elif (suppliertown == '') or ((re.match(r"[\s]", string = suppliertown)) == True):
					WrongLabelText = 'Town must have a value!'

					wrongformat(WrongLabelText)
				elif ((re.match(r"[0-9]", string = suppliertown)) == True):
					WrongLabelText = 'Town cannot just be a number!'

					wrongformat(WrongLabelText)

				elif (suppliercounty == '') or ((re.match(r"[\s]", string = suppliercounty)) == True):
					WrongLabelText = 'County cannot be empty!'

					wrongformat(WrongLabelText)
				elif ((re.match(r"[0-9]", string = suppliercounty)) == True):
					WrongLabelText = 'County cannot just be a number!'

					wrongformat(WrongLabelText)

				elif (supplierpostcode == '') or ((re.match(r"[\s]", string = supplierpostcode)) == True):
					WrongLabelText = 'Postcode cannot be empty!'

					wrongformat(WrongLabelText)
				elif ((re.match(r"[0-9]", string = supplierpostcode)) == True):
					WrongLabelText = 'Postcode cannot be a number!'

					wrongformat(WrongLabelText)

				elif (supplieremail == '') or ((re.match(r"[\s]", string = supplieremail)) == True):
					WrongLabelText = 'Email cannot be empty!'

					wrongformat(WrongLabelText)
				elif ((re.match(r"[0-9]", supplieremail)) == True):
					WrongLabelText = 'Email cannot be a number!'

					wrongformat(WrongLabelText)

				elif (supplierphone == '') or ((re.match(r"[\s]", string = supplierphone)) == True):
					WrongLabelText = 'Phone cannot be empty!'

					wrongformat(WrongLabelText)
				elif ((re.match(r"[0-9]{11}", string = supplierphone)) != True):
					WrongLabelText = 'Phone number must be 11 digits and must only contain numbers!'

					wrongformat(WrongLabelText)
				else:
					formats = True
					print("Regex checks complete.")
	
		def addsupplier():
			global suppliername, al1, al2, suppliertown, suppliercounty, supplierpostcode, supplieremail, supplierphone

			suppliername = Entry0.get()
			al1 = Entry1.get()
			al2 = Entry2.get()
			suppliertown = Entry3.get()
			suppliercounty = Entry4.get()
			supplierpostcode = Entry5.get()
			supplieremail = Entry6.get()
			supplierphone = Entry7.get()

			supplierregexcheck()

			#newsuppliersql = '''INSERT INTO "Supplier" (Name, AddressLine1, AddressLine2, Town, County, Postcode, Email, Telephone) VALUES (%s, %s, %s, %s, %s, %s, %s, %s);'''

			global SupplierList
			SupplierList.append(suppliername)

			global cur
			print("Executing SQL...")
			cur.execute('''INSERT INTO "Supplier" (Name, AddressLine1, AddressLine2, Town, County, Postcode, Email, Telephone) VALUES (%s, %s, %s, %s, %s, %s, %s, %s);''', (suppliername, al1, al2, suppliertown, suppliercounty, supplierpostcode, supplieremail, supplierphone))
			print("SQL executed.")

			print("Writing to file...")
			with open("supplierlist.txt", "w") as outfile:
				json.dump(SupplierList, outfile)				#Saves the new appended list to the text file
			print("Committed to file.")

			Entry0.delete(0, 'end')
			Entry1.delete(0, 'end')
			Entry2.delete(0, 'end')
			Entry3.delete(0, 'end')
			Entry4.delete(0, 'end')
			Entry5.delete(0, 'end')
			Entry6.delete(0, 'end')
			Entry7.delete(0, 'end')


		Frame0 = Frame(newsupplier, borderwidth = 2)
		Frame0.grid(column = 0, row = 0, columnspan = 3)

		Frame1 = Frame(newsupplier, borderwidth = 2)
		Frame1.grid(column = 0, row = 1)

		Frame2 = Frame(newsupplier, borderwidth = 2)
		Frame2.grid(column = 1, row = 1)

		Frame3 = Frame(newsupplier, borderwidth = 5)
		Frame3.grid(column = 1, row = 2)

		################# LABELS #####################

		TitleLabel = Label(Frame0, text = 'NEW SUPPLIER!')
		TitleLabel.grid(column = 1, row = 0)

		Label0 = Label(Frame1, text = 'Name:', borderwidth = 2)
		Label0.grid(column = 0, row = 0, sticky = W)

		Label1 = Label(Frame1, text = 'Address Line 1:', borderwidth = 2)
		Label1.grid(column = 0, row = 1, sticky = W)

		Label2 = Label(Frame1, text = 'Address Line 2:', borderwidth = 2)
		Label2.grid(column = 0, row = 2, sticky = W)

		Label3 = Label(Frame1, text = 'Town:', borderwidth = 2)
		Label3.grid(column = 0, row = 4, sticky = W)

		Label4 = Label(Frame1, text = 'County:', borderwidth = 2)
		Label4.grid(column = 0, row = 5, sticky = W)

		Label5 = Label(Frame1, text = 'Postcode:', borderwidth = 2)
		Label5.grid(column = 0, row = 6, sticky = W)

		Label6 = Label(Frame1, text = 'Email:', borderwidth = 2)
		Label6.grid(column = 0, row = 7, sticky = W)

		Label7 = Label(Frame1, text = 'Telephone:', borderwidth = 2)
		Label7.grid(column = 0, row = 8, sticky = W)

		################# ENTRY BOXES ################

		Entry0 = Entry(Frame2, width = 25, borderwidth = 2)			#Supplier name
		Entry0.grid(column = 0, row = 0, columnspan = 2)

		Entry1 = Entry(Frame2, width = 25, borderwidth = 2)			#Address Line 1
		Entry1.grid(column = 0, row = 1, columnspan = 2)

		Entry2 = Entry(Frame2, width = 25, borderwidth = 2)			#Address Line 2
		Entry2.grid(column = 0, row = 2, columnspan = 2)

		Entry3 = Entry(Frame2, width = 25, borderwidth = 2)			#Town
		Entry3.grid(column = 0, row = 3, columnspan = 2)

		Entry4 = Entry(Frame2, width = 25, borderwidth = 2)			#County
		Entry4.grid(column = 0, row = 4, columnspan = 2)

		Entry5 = Entry(Frame2, width = 25, borderwidth = 2)			#Postcode
		Entry5.grid(column = 0, row = 5, columnspan = 2)

		Entry6 = Entry(Frame2, width = 25, borderwidth = 2)			#Email
		Entry6.grid(column = 0, row = 6, columnspan = 2)

		Entry7 = Entry(Frame2, width = 25, borderwidth = 2)			#Telephone
		Entry7.grid(column = 0, row = 7, columnspan = 2)


		SubmitButton = Button(Frame3, text = 'SUBMIT', command = addsupplier)
		SubmitButton.grid(column = 0, row = 8, sticky = E)

		def exitsupplier():
			global newsupplier
			newsupplier.destroy()

		ExitButton = Button(Frame0, text = 'X', command = exitsupplier)
		ExitButton.grid(column = 3, row = 0, sticky = E)

		newsupplier.mainloop()

	def lookupitem():
		global lookupitem
		lookupitem = Tk()

		def killwindow():
			global lookupitem
			lookupitem.destroy()

		def deleteitem():
			global warning
			warning.destroy()

			global starteritemlist, mainsitemlist, dessertitemlist, NameListbox, CategoryListbox
			starteritemlistfile = open("starteritemlist.txt")
			starteritemlist = json.loads(starteritemlistfile.read())
			
			mainsitemlistfile = open("mainsitemlist.txt")
			mainsitemlist = json.loads(mainsitemlistfile.read())


			dessertitemlistfile = open("dessertitemlist.txt")
			dessertitemlist = json.loads(dessertitemlistfile.read())


			deleting = (str(NameListbox.get(0)),)
			course = (str(CategoryListbox.get(0)))
			#deleteSQL = '''DELETE FROM "Items" WHERE ("Items".ItemName = %s) DELETE FROM "Stock" WHERE "Stock".ItemID = (SELECT "Items".ID FROM "Items" WHERE "Items".ItemName = %s);'''
			
			global con, cur
			print("Executing SQL...")
			cur.execute('''DELETE FROM "Items" WHERE ("Items".ItemName = %s); DELETE FROM "Stock" WHERE "Stock".ItemID = (SELECT "Items".ID FROM "Items" WHERE "Items".ItemName = %s);''', [deleting, deleting])
			print("SQL executed.")

			deleting = (str(NameListbox.get(0)))

			print("Writing to file...")
						
			if (course == ('Starter')):
				starteritemlist.remove(deleting)
				with open("starteritemlist.txt", "w") as outfile:
					json.dump(starteritemlist, outfile)				#Saves the new appended list to the text file
			elif (course == ('Main')):
				mainsitemlist.remove(deleting)
				with open("mainsitemlist.txt", "w") as outfile:
					json.dump(mainsitemlist, outfile)				#Saves the new appended list to the text file
			elif (course == ('Dessert')):
				dessertitemlist.remove(deleting)
				with open("dessertitemlist.txt", "w") as outfile:
					json.dump(dessertitemlist, outfile)				#Saves the new appended list to the text file

			print("Written to file.")
			NameListbox.delete(0, END)
			DescListbox.delete(0, END)
			CategoryListbox.delete(0, END)
			QuantityListbox.delete(0, END)
			SupplierListbox.delete(0, END)
			populatelistbox()

		def closewarning():
			global warning
			warning.destroy()

		def warning():
			global warning
			warning = Tk()
			Frame0 = Frame(warning, borderwidth = 2)
			Frame0.grid(column = 0, row = 0)
			WarningLabel = Label(Frame0, text = 'Are you sure you wish to delete this item?')
			WarningLabel.grid(column = 0, row = 0, columnspan = 2)

			YesButton = Button(Frame0, text = 'Yes', borderwidth = 2, command = deleteitem)
			YesButton.grid(column = 0, row = 1)

			NoButton = Button(Frame0, text = 'No', borderwidth = 2, command = closewarning)
			NoButton.grid(column = 1, row = 1)

		def incquantity():
			global cur, NameListbox
			itemname = NameListbox.get(0)
			cur.execute('''UPDATE "Stock" SET Quantity = (Quantity + 1) WHERE ("Stock".ItemID = (SELECT "Items".ID FROM "Items" WHERE ("Items".ItemName = %s)));''', [itemname])
			currentqty = QuantityListbox.get(0)
			QuantityListbox.delete(0, END)
			QuantityListbox.insert(0, (currentqty + 1))
			populatelistbox()


		def decquantity():
			global cur, NameListbox
			itemname = NameListbox.get(0)
			cur.execute('''UPDATE "Stock" SET Quantity = (Quantity - 1) WHERE ("Stock".ItemID = (SELECT "Items".ID FROM "Items" WHERE ("Items".ItemName = %s)));''', [itemname])
			currentqty = QuantityListbox.get(0)
			QuantityListbox.delete(0, END)
			QuantityListbox.insert(0, (currentqty - 1))
			populatelistbox()


		################ FRAMES ###################

		Frame0 = Frame(lookupitem, borderwidth = 2)
		Frame0.grid(column = 0, row = 0, columnspan = 2)

		Frame1 = Frame(lookupitem, borderwidth = 2)
		Frame1.grid(column = 0, row = 1)

		Frame2 = Frame(lookupitem, borderwidth = 2)
		Frame2.grid(column = 1, row = 1)

		Frame3 = Frame(lookupitem, borderwidth = 2)
		Frame3.grid(column = 1, row = 2)

		###########################################

		############# LABELS ######################

		Label0 = Label(Frame0, text = 'LOOK UP ITEM')
		Label0.grid(column = 0, row = 0, columnspan = 2)

		Label1 = Label(Frame1, text = 'Name:')
		Label1.grid(column = 0, row = 0)

		Label2 = Label(Frame1, text = 'Description:')
		Label2.grid(column = 0, row = 1)

		Label3 = Label(Frame1, text = 'Category:')
		Label3.grid(column = 0, row = 2)

		Label4 = Label(Frame1, text = 'Quantity:')
		Label4.grid(column = 0, row = 3)

		Label5 = Label(Frame1, text = 'Supplier:')
		Label5.grid(column = 0, row = 4)

		###########################################

		############### LISTBOXES #################

		global NameListbox
		NameListbox = Listbox(Frame2, height = 1, width = 20, borderwidth = 2)
		NameListbox.grid(column = 0, row = 0)

		DescListbox = Listbox(Frame2, height = 1, width = 20, borderwidth = 2)
		DescListbox.grid(column = 0, row = 1)

		global CategoryListbox
		CategoryListbox = Listbox(Frame2, height = 1, width = 20, borderwidth = 2)
		CategoryListbox.grid(column = 0, row = 2)

		QuantityListbox = Listbox(Frame2, height = 1, width = 5, borderwidth = 2)
		QuantityListbox.grid(column = 0, row = 3, sticky = W)

		SupplierListbox = Listbox(Frame2, height = 1, width = 20, borderwidth = 2)
		SupplierListbox.grid(column = 0, row = 4)

		############################################

		IncQuantity = Button(Frame2, text = '+', command = incquantity)
		IncQuantity.grid(column = 1, row = 3)

		DecQuantity = Button(Frame2, text = '-', command = decquantity)
		DecQuantity.grid(column = 2, row = 3)

		OkButton = Button(Frame3, text = 'Ok', command = killwindow)
		OkButton.grid(column = 1, row = 0, sticky = E)

		DeleteButton = Button(Frame3, text = 'Delete', command = warning)
		DeleteButton.grid(column = 0, row = 0, sticky = W)


		selecteditem = Display.selection()
		ammenditem = Display.set(selecteditem, column = 0)
		newstring = str(ammenditem)

		#editSQL = '''SELECT "Items".ItemName, "Items".ItemDescription, "Items".ItemCategory, "Stock".Quantity, "Supplier".Name AS SupplierName FROM "Items" INNER JOIN "Stock" ON "Stock".ItemID = "Items".ID INNER JOIN "Supplier" ON "Supplier".ID = "Stock".SupplierID'''
		global con, cur
		cur.execute('''SELECT "Items".ItemName, "Items".ItemDescription, "Items".ItemCategory, "Stock".Quantity, "Supplier".Name AS SupplierName FROM "Items" INNER JOIN "Stock" ON "Stock".ItemID = "Items".ID INNER JOIN "Supplier" ON "Supplier".ID = "Stock".SupplierID WHERE ("Items".ItemName = %s);''', [newstring])

		x = []
		x = cur.fetchone()
		NameListbox.insert(END, x[0])
		DescListbox.insert(END, x[1])
		CategoryListbox.insert(END, x[2])
		QuantityListbox.insert(END, x[3])
		SupplierListbox.insert(END, x[4])


		lookupitem.mainloop()

	Frame0 = Frame(stock)
	Frame0.grid(column = 0, row = 0)

	Frame1 = Frame(stock)
	Frame1.grid(column = 1, row = 0)

	Frame2 = Frame(stock)
	Frame2.grid(column = 2, row = 0)

	Label0 = Label(Frame0, text = 'Stock Control')
	Label0.grid(column = 0, row = 0)

	Button0 = Button(Frame0, text = 'LoadDB', command = loaddb)
	Button0.grid(column = 0, row = 1)

	Button1 = Button(Frame0, text = 'New DB', command = createdb)
	Button1.grid(column = 0, row = 2)

	Label1 = Label(Frame0, text = 'Current DB: ')
	Label1.grid(column = 0, row = 12)

	Label2 = Label(Frame0, text = currentDB)
	Label2.grid(column = 0, row = 13)



	def showstarters():
		#contentssql = '''SELECT "Items".ItemName, "Stock".Quantity, "Supplier".Name AS SupplierName FROM "Items" INNER JOIN "Stock" ON "Stock".ItemID = "Items".ID INNER JOIN "Supplier" ON "Supplier".ID = "Stock".SupplierID ORDER BY "Items".ItemName ASC;'''
		global con, cur
		cur.execute('''SELECT "Items".ItemName, "Stock".Quantity, "Supplier".Name AS SupplierName FROM "Items" INNER JOIN "Stock" ON "Stock".ItemID = "Items".ID INNER JOIN "Supplier" ON "Supplier".ID = "Stock".SupplierID WHERE "Items".ItemCategory = 'Starter' ORDER BY "Items".ItemName ASC;''')

		Display.delete(*Display.get_children())		
		for item in cur.fetchall():
			Display.insert('', 'end', values = item)

	def showmains():
		#contentssql = '''SELECT "Items".ItemName, "Stock".Quantity, "Supplier".Name AS SupplierName FROM "Items" INNER JOIN "Stock" ON "Stock".ItemID = "Items".ID INNER JOIN "Supplier" ON "Supplier".ID = "Stock".SupplierID ORDER BY "Items".ItemName ASC;'''
		global con, cur
		cur.execute('''SELECT "Items".ItemName, "Stock".Quantity, "Supplier".Name AS SupplierName FROM "Items" INNER JOIN "Stock" ON "Stock".ItemID = "Items".ID INNER JOIN "Supplier" ON "Supplier".ID = "Stock".SupplierID WHERE "Items".ItemCategory = 'Main' ORDER BY "Items".ItemName ASC;''')
		
		Display.delete(*Display.get_children())		
		for item in cur.fetchall():
			Display.insert('', 'end', values = item)

	def showdesserts():
		#contentssql = '''SELECT "Items".ItemName, "Stock".Quantity, "Supplier".Name AS SupplierName FROM "Items" INNER JOIN "Stock" ON "Stock".ItemID = "Items".ID INNER JOIN "Supplier" ON "Supplier".ID = "Stock".SupplierID ORDER BY "Items".ItemName ASC;'''
		global con, cur
		cur.execute('''SELECT "Items".ItemName, "Stock".Quantity, "Supplier".Name AS SupplierName FROM "Items" INNER JOIN "Stock" ON "Stock".ItemID = "Items".ID INNER JOIN "Supplier" ON "Supplier".ID = "Stock".SupplierID WHERE "Items".ItemCategory = 'Dessert' ORDER BY "Items".ItemName ASC;''')
		
		Display.delete(*Display.get_children())
		for item in cur.fetchall():
			Display.insert('', 'end', values = item)

	Button2 = Button(Frame1, text = 'STARTERS', command = showstarters)
	Button2.grid(column = 0, row = 2)

	Button3 = Button(Frame1, text = 'MAINS', command = showmains)
	Button3.grid(column = 0, row = 3)

	Button4 = Button(Frame1, text = 'DESSERTS', command = showdesserts)
	Button4.grid(column = 0, row = 4)

	Button5 = Button(Frame1, text = 'ALL', command = populatelistbox)
	Button5.grid(column = 0, row = 5)


	Display = ttk.Treeview(Frame2, selectmode = "extended", columns = ("Item", "Quantity", "Supplier"), show = "headings")
	vsb = ttk.Scrollbar(Frame2, orient = "vertical", command = Display.yview)		#Creates a vertical scrollbar
	Display.configure(yscrollcommand = vsb.set)										#Configures scrollbar
	Display.grid(column = 0, row = 0, sticky = 'nsew', columnspan = 3)
	vsb.grid(column = 3, row = 0, sticky = 'ns')
	Frame2.grid_columnconfigure(0, weight = 1)
	Frame2.grid_rowconfigure(0, weight = 1)

	Display.heading("Item", text = "Item")
	Display.column("Item", minwidth = 140, stretch = NO)			#Adjust the size of the columns
	Display.heading("Quantity", text = "Quantity")
	Display.column("Quantity", minwidth = 15, stretch = NO)
	Display.heading("Supplier", text = "Supplier")
	Display.column("Supplier", minwidth = 120, stretch = NO)

	Button6 = Button(Frame2, text = 'ADD NEW ITEM', command = addnewitem)
	Button6.grid(column = 0, row = 14, sticky = W)

	Button7 = Button(Frame2, text = 'ADD NEW SUPPLIER', command = newsupplier)
	Button7.grid(column = 1, row = 14, sticky = W)

	Button8 = Button(Frame2, text = 'EDIT ITEM', command = lookupitem)
	Button8.grid(column = 2, row = 14, sticky = W)


	stock.mainloop()


def OrderGUI():
	order = Tk()
	order.title('Ordering System')

	def EditCourse(meal):
		edit = Tk()

		##################### DECLARE VARIABLES ################

		global pendingorder1
		pendingorder1 = [""]

		global pendingorder2
		pendingorder2 = [""]

		global pendingorder3
		pendingorder3 = [""]

		global pendingorder4
		pendingorder4 = [""]

		global pendingorder5
		pendingorder5 = [""]

		global pendingorder6
		pendingorder6 = [""]

		########################################################


		Frame0 = Frame(edit)
		Frame0.grid(column = 0, row = 0)
		
		Frame1 = Frame(edit)
		Frame1.grid(column = 1, row = 0)

		Frame2 = Frame(edit)
		Frame2.grid(column = 2, row = 0)

		Label0 = Label(Frame0, text = 'Logo')
		Label0.grid(column = 0, row = 0)

		Label1 = Label(Frame0, text = 'Prepared:')
		Label1.grid(column = 0, row = 1)

		Label2 = Label(Frame0, text = 'With:')
		Label2.grid(column = 0, row = 2)

		Label3 = Label(Frame0, text = 'Without:')
		Label3.grid(column = 0, row = 3)

		Label4 = Label(Frame0, text = 'Misc. :')
		Label4.grid(column = 0, row = 4)

		BlankLabel = Label(Frame2, text = '')
		BlankLabel.grid(column = 0, row = 0)

		CurrentDish = Listbox(Frame1, height = 1, width = 17, borderwidth = 3)
		CurrentDish.grid(column = 0, row = 0)

		CurrentDish.insert(END, meal) #Places the current selection into listbox

		types = ("Blue", "Rare", "Medium-Rare", "Medium", "Medium-Well", "Well-Done")
		cb0 = ttk.Combobox(Frame1, values = types, state='readonly', width = 15)
		cb0.current(1)  # set selection
		cb0.grid(column = 0, row = 1)

		Entry1 = Entry(Frame1, width = 17, borderwidth = 3)
		Entry1.grid(column = 0, row = 2)

		Entry2 = Entry(Frame1, width = 17, borderwidth = 3)
		Entry2.grid(column = 0, row = 3)

		Entry3 = Entry(Frame1, width = 17, borderwidth = 3)
		Entry3.grid(column = 0, row = 4)

		def submitprepared():
			indent = ' - '
			change = cb0.get()

			final = indent + change
			CurrentOrder.insert((CurrentOrder.index(ACTIVE) + 1), final)
			CurrentOrder.itemconfig((CurrentOrder.index(ACTIVE)) + 1, background = 'yellow', foreground = 'red')

		Button0 = Button(Frame2, text = '>', command = submitprepared)
		Button0.grid(column = 0, row = 1)

		def submitwith():
			indent = ' - w/'
			change = Entry1.get()

			final = indent + change
			CurrentOrder.insert((CurrentOrder.index(ACTIVE) + 1), final)
			CurrentOrder.itemconfig((CurrentOrder.index(ACTIVE)) + 1, background = 'yellow', foreground = 'red')

		Button1 = Button(Frame2, text = '>', command = submitwith)
		Button1.grid(column = 0, row = 2)

		def submitwithout():
			indent = ' - w/o '
			change = Entry2.get()

			final = indent + change
			CurrentOrder.insert((CurrentOrder.index(ACTIVE) + 1), final)
			CurrentOrder.itemconfig((CurrentOrder.index(ACTIVE)) + 1, background = 'yellow', foreground = 'red')

		Button2 = Button(Frame2, text = '>', command = submitwithout)
		Button2.grid(column = 0, row = 3)

		def submitmisc():
			indent = ' - '
			change = Entry3.get()

			final = indent + change
			CurrentOrder.insert((CurrentOrder.index(ACTIVE) + 1), final)
			CurrentOrder.itemconfig((CurrentOrder.index(ACTIVE)) + 1, background = 'yellow', foreground = 'red')

		Button3 = Button(Frame2, text = '>', command = submitmisc)
		Button3.grid(column = 0, row = 4)

		edit.title('Edit Course')
		edit.mainloop()


	Frame0 = Frame(order,	borderwidth = 2)
	Frame0.grid(column = 0, row = 0)

	Frame1 = Frame(order,	borderwidth = 2)
	Frame1.grid(column = 0, row = 1)

	Frame2 = Frame(order,   borderwidth = 2)
	Frame2.grid(column = 0, row = 2)

	Frame3 = Frame(order,   borderwidth = 2)
	Frame3.grid(column = 1, row = 0)

	Frame4 = Frame(order,	borderwidth = 2)
	Frame4.grid(column = 1, row = 1, rowspan = 2)

	############### FRAME 0 #################

	Label1 = Label(Frame0, text = "Food Orders")
	Label1.grid(column = 0, row = 0)

	################# FRAME 4 ################

	global CurrentOrder #Allows other functions to add to it
	CurrentOrder = Listbox(Frame4, height = 25, width = 40)
	CurrentOrder.grid(column = 0, row = 1, sticky = N)

	def currentorders():

		global pendingorder1, pendingorder2, pendingorder3, pendingorder4, pendingorder5, pendingorder6
		a = Listbox0.size()
		b = Listbox1.size()
		c = Listbox2.size()
		d = Listbox3.size()
		e = Listbox4.size()
		f = Listbox5.size()

		x = CurrentOrder.size()
		contents = CurrentOrder.get(0, END)
		tableno = ts.get()
		currenttime = datetime.now().strftime('%H:%M:%S')

		ordertitle = '------' + ' Table #' + tableno + ' --- ' + currenttime + ' -----'

		OrderSent = False

		while (OrderSent == False):

			if (a == 0):
				for i in range(0, x):
					Listbox0.insert(END, contents[i])
					if (Listbox0.get(i).startswith(' - ') == True):
						Listbox0.itemconfig(i, background = 'yellow', foreground = 'red')
				
				Listbox0.insert(0, ordertitle)
				Listbox0.itemconfig(0, background = '#B4E6FF')
				CurrentOrder.delete(0, END)

				OrderSent = True

			elif (b == 0):
				for i in range(0, x):
					Listbox1.insert(END, contents[i])
					if (Listbox1.get(i).startswith(' - ') == True):
						Listbox1.itemconfig(i, background = 'yellow', foreground = 'red')

				Listbox1.insert(0, ordertitle)
				Listbox1.itemconfig(0, background = '#B4E6FF')
				CurrentOrder.delete(0, END)

				OrderSent = True

			elif (c == 0):
				for i in range(0, x):
					Listbox2.insert(END, contents[i])
					if (Listbox2.get(i).startswith(' - ') == True):
						Listbox2.itemconfig(i, background = 'yellow', foreground = 'red')

				Listbox2.insert(0, ordertitle)
				Listbox2.itemconfig(0, background = '#B4E6FF')				
				CurrentOrder.delete(0, END)

				OrderSent = True
				
			elif (d == 0):
				for i in range(0, x):
					Listbox3.insert(END, contents[i])
					if (Listbox3.get(i).startswith(' - ') == True):
						Listbox3.itemconfig(i, background = 'yellow', foreground = 'red')

				Listbox3.insert(0, ordertitle)
				Listbox3.itemconfig(0, background = '#B4E6FF')				
				CurrentOrder.delete(0, END)

				OrderSent = True
			
			elif (e == 0):
				for i in range(0, x):
					Listbox4.insert(END, contents[i])
					if (Listbox4.get(i).startswith(' - ') == True):
						Listbox4.itemconfig(i, background = 'yellow', foreground = 'red')

				Listbox4.insert(0, ordertitle)
				Listbox4.itemconfig(0, background = '#B4E6FF')				
				CurrentOrder.delete(0, END)

				OrderSent = True

			elif (f == 0):
				for i in range(0, x):
					Listbox5.insert(END, contents[i])
					if (Listbox5.get(i).startswith(' - ') == True):
						Listbox5.itemconfig(i, background = 'yellow', foreground = 'red')

				Listbox5.insert(0, ordertitle)				
				Listbox5.itemconfig(0, background = '#B4E6FF')
				CurrentOrder.delete(0, END)

				OrderSent = True				

			else:				#Stores current order as a list and clears CurrentOrder
				if (pendingorder1[0] == ""):
					pendingorder1.remove("")	
					pendingorder1.insert(0, ordertitle)
					for i in range(1, x):
						nxtobj = CurrentOrder.get(i)
						pendingorder1.append(nxtobj)	#Append list with every element of listbox
				elif (pendingorder2[0] == ""):
					pendingorder2.insert(0, ordertitle)
					for i in range(1, x):
						nxtobj = CurrentOrder.get(i)
						pendingorder2.append(nxtobj)
				elif (pendingorder3[0] == ""):
					pendingorder3.insert(0, ordertitle)
					for i in range(1, x):
						nxtobj = CurrentOrder.get(i)
						pendingorder3.append(nxtobj)
				elif (pendingorder4[0] == ""):
					pendingorder4.insert(0, ordertitle)
					for i in range(1, x):
						nxtobj = CurrentOrder.get(i)
						pendingorder4.append(nxtobj)
				elif (pendingorder5[0] == ""):
					pendingorder5.insert(0, ordertitle)
					for i in range(1, x):
						nxtobj = CurrentOrder.get(i)
						pendingorder5.append(nxtobj)
				elif (pendingorder6[0] == ""):
					pendingorder6.insert(0, ordertitle)
					for i in range(1, x):
						nxtobj = CurrentOrder.get(i)
						pendingorder6.append(nxtobj)

				CurrentOrder.delete(0, END)
				print('Pendingorder1 is ', pendingorder1)
				print('Pendingorder2 is ', pendingorder2)
				print('Pendingorder3 is ', pendingorder3)
				print('Pendingorder4 is ', pendingorder4)
				print('Pendingorder5 is ', pendingorder5)
				print('Pendingorder6 is ', pendingorder6)

				OrderSent = True


	def GetMeal():
		
		x = CurrentOrder.size()

		if (x == 0):
			alert()
		else:
			meal = CurrentOrder.get(ACTIVE) #Retreives current selection
			EditCourse(meal) #Pass to EditCourse function

	def removeitem():
		CurrentOrder.delete(ACTIVE) #Removes selection from listbox

	def alert():
		
		def close():
			alert.destroy()

		alert = Tk()

		Frame0 = Frame(alert, borderwidth = 2)
		Frame0.grid(column = 0, row = 0)

		Label0 = Label(alert, text = 'Current order is empty!')
		Label0.grid(column = 0, row = 0)

		CloseButton = Button(alert, text = 'Ok', command = close)
		CloseButton.grid(column = 0, row = 1)

	def submit():

		def noneleft(message):

			global errormessage
			errormessage = True

			def killok():
				global none
				none.destroy()

			global none
			none = Tk()

			Frame0 = Frame(none, borderwidth = 2)
			Frame0.grid(column = 0, row = 0)

			Label0 = Label(Frame0, text = message, borderwidth = 1)
			Label0.grid(column = 0, row = 0)

			Button0 = Button(Frame0, text = 'Ok', command = killok)
			Button0.grid(column = 0, row = 1)

		x = CurrentOrder.size()
		a = 0
		global errormessage
		errormessage = False

		if (x == 0):
			alert()

		else:
			checked = []
			for i in range(0, (x)):
				obj = CurrentOrder.get(i)
				while (not obj in checked):
					if obj.startswith(' - ') or obj.startswith('*****'):
						checked.append(obj)
					else:	
						z = 0
						for s in range(0, (x)):
							if (CurrentOrder.get(s) == obj):
								z = z + 1
						cur.execute('''SELECT "Stock".Quantity FROM "Stock" WHERE ("Stock".ItemID = (SELECT "Items".ID FROM "Items" WHERE ("Items".ItemName = %s)));''', [obj])
						stockleft = (cur.fetchone())
						stockleft =  reduce(lambda rst, d: rst * 10 + d, stockleft)
						if (z > stockleft):
							message = 'There is only ' + str(stockleft) + ' ' + str(obj) + ' ' +' left!'
							noneleft(message)
							checked.append(obj)
						else:
							checked.append(obj)
			if (errormessage == False):
				for i in checked:
					cur.execute('''UPDATE "Stock" SET Quantity = (Quantity - 1) WHERE ("Stock".ItemID = (SELECT "Items".ID FROM "Items" WHERE ("Items".ItemName = %s)));''', [i])
				currentorders()


	Label5 = Label(Frame4, text = "CURRENT ORDER")
	Label5.grid(column = 0, row = 0, sticky = N)

	EditButton = Button(Frame4, text = 'Edit', command = GetMeal)
	EditButton.grid(column = 0, row = 0, sticky = E)

	RemoveButton = Button(Frame4, text = 'X', command = removeitem)
	RemoveButton.grid(column = 1, row = 0, sticky = E)

	SubmitButton = Button(Frame4, text = 'SUBMIT', command = submit)
	SubmitButton.grid(column = 0, row = 2, sticky = E)

	############### FRAME 1 #################

	Label2 = Label(Frame1, text = "STARTERS")
	Label2.grid(column = 0, row = 0)

	StarterList = Listbox(Frame1, height = 15, width = 25)
	StarterList.grid(column = 0, row = 1)

	global starteritemlist
	starteritemlist = json.load(open("starteritemlist.txt", "r"))

	for item in starteritemlist:
		StarterList.insert(END, item)

	def StarterAdd():

		additem = StarterList.get(ACTIVE)
		CurrentOrder.insert(0, additem)

	def NextCourse():
		global separator
		separator = "****************************************"			#Adds a separator after the most recently entered course
		CurrentOrder.insert(END, separator)


	StarterAdd = Button(Frame1, text = "ADD", command = StarterAdd)
	StarterAdd.grid(column = 1, row = 1)

	StarterNone = Button(Frame1, text = "Separator", command = NextCourse)
	StarterNone.grid(column = 0, row = 2, sticky = E)


	################# FRAME 2 ###############

	Label3 = Label(Frame2, text = "MAINS")
	Label3.grid(column = 0, row = 0)

	MainsList = Listbox(Frame2, height = 15, width = 25)
	MainsList.grid(column = 0, row = 1)

	def MainsAdd():
		additem = MainsList.get(ACTIVE)
		CurrentOrder.insert(END, additem)


	global mainsitemlist
	mainsitemlist = json.load(open("mainsitemlist.txt", "r"))

	for item in mainsitemlist:
		MainsList.insert(END, item)

	MainsAdd = Button(Frame2, text = "ADD", command = MainsAdd)
	MainsAdd.grid(column = 1, row = 1)


	################ FRAME 3 ################

	Label4 = Label(Frame3, text = "Table No.")
	Label4.grid(column = 0, row = 0)

	tables = ("1", "2", "3", "6", "7", "8", "9", "10", "11", "12", "13", "14", 
			  "15", "16", "101", "103", "104", "105", "106", "107", "108", "109", 
			  "110", "111", "112", "113", "114")
	global ts
	ts = ttk.Combobox(Frame3, values = tables, state='readonly', width = 4)
	ts.current(1)  # set selection
	ts.grid(column = 1, row = 0)



	order.mainloop()


def KitchenGUI():
	kitchen = Tk()
	kitchen.title('Display System')

	############ DECLARE VARIABLES ############

	global Listbox0clear
	Listbox0clear = False

	global Listbox1clear 						# Variables for transferring data across
	Listbox1clear = False 						# multiple listboxes

	global Listbox2clear
	Listbox2clear = False

	global Listbox3clear
	Listbox3clear = False

	global Listbox4clear
	Listbox4clear = False

	global Listbox5clear
	Listbox5clear = False

	##########################################

	TitleFrame = Frame(kitchen, borderwidth = 2)
	TitleFrame.grid(column = 0, row = 0)

	TitleLabel = Label(TitleFrame, text = 'CURRENT ORDERS')
	TitleLabel.grid(column = 1, row = 0)

	Frame0 = Frame(kitchen, borderwidth = 2)
	Frame0.grid(column = 0, row = 1)

	Frame1 = Frame(kitchen, borderwidth = 2)
	Frame1.grid(column = 3, row = 1)

	Frame2 = Frame(kitchen, borderwidth = 2)
	Frame2.grid(column = 6, row = 1)

	Frame3 = Frame(kitchen, borderwidth = 2)
	Frame3.grid(column = 0, row = 3)

	Frame4 = Frame(kitchen, borderwidth = 2)
	Frame4.grid(column = 3, row = 3)

	Frame5 = Frame(kitchen, borderwidth = 2)
	Frame5.grid(column = 6, row = 3)

	########################################################

	global Listbox0
	Listbox0 = Listbox(Frame0, width = 30, height = 15)
	Listbox0.grid(column = 0, row = 0, columnspan = 3)

	global Listbox1
	Listbox1 = Listbox(Frame1, width = 30, height = 15)
	Listbox1.grid(column = 0, row = 0, columnspan = 3)

	global Listbox2
	Listbox2 = Listbox(Frame2, width = 30, height = 15)
	Listbox2.grid(column = 0, row = 0, columnspan = 3)

	global Listbox3
	Listbox3 = Listbox(Frame3, width = 30, height = 15)
	Listbox3.grid(column = 0, row = 0, columnspan = 3)

	global Listbox4
	Listbox4 = Listbox(Frame4, width = 30, height = 15)
	Listbox4.grid(column = 0, row = 0, columnspan = 3)

	global Listbox5
	Listbox5 = Listbox(Frame5, width = 30, height = 15)
	Listbox5.grid(column = 0, row = 0, columnspan = 3)

	########################################################

	ButtonFrame0 = Frame(kitchen, borderwidth = 2)
	ButtonFrame0.grid(column = 0, row = 2)

	ButtonFrame1 = Frame(kitchen, borderwidth = 2)
	ButtonFrame1.grid(column = 3, row = 2)

	ButtonFrame2 = Frame(kitchen, borderwidth = 2)
	ButtonFrame2.grid(column = 6, row = 2)

	ButtonFrame3 = Frame(kitchen, borderwidth = 2)
	ButtonFrame3.grid(column = 0, row = 4)

	ButtonFrame4 = Frame(kitchen, borderwidth = 2)
	ButtonFrame4.grid(column = 3, row = 4)

	ButtonFrame5 = Frame(kitchen, borderwidth = 2)
	ButtonFrame5.grid(column = 6, row = 4)

	########################################################

	def listbox0tablecleared():
		global Listbox0clear
		a = Listbox0.size()
		if (a > 0):
			for i in range(1, a):
				if ((Listbox0.get(i).startswith('****')) == False):
					Listbox0.itemconfig(i, background = 'green')
				else:
					break
			Listbox0clear = True
		
	def listbox1tablecleared():
		global Listbox1clear	
		a = Listbox1.size()
		if (a > 0):
			for i in range(1, a):
				if ((Listbox1.get(i).startswith('****')) == False):
					Listbox1.itemconfig(i, background = 'green')
				else:
					break
			Listbox1clear = True

	def listbox2tablecleared():
		global Listbox2clear
		a = Listbox2.size()
		if (a > 0):
			for i in range(1, a):
				if ((Listbox2.get(i).startswith('****')) == False):
					Listbox2.itemconfig(i, background = 'green')
				else:
					break

		Listbox2clear = True

	def listbox3tablecleared():
		global Listbox3clear
		a = Listbox3.size()
		if (a > 0):
			for i in range(1, a):
				if ((Listbox3.get(i).startswith('****')) == False):
					Listbox3.itemconfig(i, background = 'green')
				else:
					break

		Listbox3clear = True

	def listbox4tablecleared():
		global Listbox4clear
		a = Listbox4.size()
		if (a > 0):
			for i in range(1, a):
				if ((Listbox4.get(i).startswith('****')) == False):
					Listbox4.itemconfig(i, background = 'green')
				else:
					break

		Listbox4clear = True

	def listbox5tablecleared():
		global Listbox5clear
		a = Listbox5.size()
		if (a > 0):
			for i in range(1, a):
				if ((Listbox5.get(i).startswith('****')) == False):
					Listbox5.itemconfig(i, background = 'green')
				else:
					break

		Listbox5clear = True

	Clear0 = Button(ButtonFrame0, text = 'CLEAR', command = listbox0tablecleared)
	Clear0.grid(column = 0, row = 0, sticky = W)

	Clear1 = Button(ButtonFrame1, text = 'CLEAR', command = listbox1tablecleared)
	Clear1.grid(column = 0, row = 0, sticky = W)

	Clear2 = Button(ButtonFrame2, text = 'CLEAR', command = listbox2tablecleared)
	Clear2.grid(column = 0, row = 0, sticky = W)

	Clear3 = Button(ButtonFrame3, text = 'CLEAR', command = listbox3tablecleared)
	Clear3.grid(column = 0, row = 0, sticky = W)

	Clear4 = Button(ButtonFrame4, text = 'CLEAR', command = listbox4tablecleared)
	Clear4.grid(column = 0, row = 0, sticky = W)

	Clear5 = Button(ButtonFrame5, text = 'CLEAR', command = listbox5tablecleared)
	Clear5.grid(column = 0, row = 0, sticky = W)

	########################################################

	def listbox0done():				#These functions shuffle the orders to the next listbox
		global Listbox0clear, Listbox1clear, Listbox2clear, Listbox3clear, Listbox4clear, Listbox5clear 		#Requirement of python to restate global variable(s)
		Listbox0.delete(0, END)
		Listbox0clear = False

		a = Listbox1.size()

		if (a > 0):
			contents = Listbox1.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox0.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox0.get(i).startswith(' - ') == True):
					Listbox0.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox0.itemconfig(0, background = '#B4E6FF')
			if (Listbox1clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox1.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox0.get(i).startswith('******')) == False):
							Listbox0.itemconfig(i, background = 'green')
						else:
							break
					Listbox0clear = True

			else:
				Listbox1clear = False				

			Listbox1.delete(0, END)

		a = Listbox2.size()

		if (a > 0):
			contents = Listbox2.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox1.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox1.get(i).startswith(' - ') == True):
					Listbox1.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox1.itemconfig(0, background = '#B4E6FF')
			if (Listbox2clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox2.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox1.get(i).startswith('******')) == False):
							Listbox1.itemconfig(i, background = 'green')
						else:
							break
					Listbox1clear = True

			else:
				Listbox2clear = False				

			Listbox2.delete(0, END)

		a = Listbox3.size()

		if (a > 0):
			contents = Listbox3.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox2.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox2.get(i).startswith(' - ') == True):
					Listbox2.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox2.itemconfig(0, background = '#B4E6FF')
			if (Listbox3clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox3.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox2.get(i).startswith('******')) == False):
							Listbox2.itemconfig(i, background = 'green')
						else:
							break
					Listbox2clear = True

			else:
				Listbox3clear = False				

			Listbox3.delete(0, END)

		a = Listbox4.size()

		if (a > 0):
			contents = Listbox4.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox3.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox3.get(i).startswith(' - ') == True):
					Listbox3.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox3.itemconfig(0, background = '#B4E6FF')
			if (Listbox4clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox4.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox3.get(i).startswith('******')) == False):
							Listbox3.itemconfig(i, background = 'green')
						else:
							break
					Listbox3clear = True

			else:
				Listbox4clear = False				

			Listbox4.delete(0, END)

		a = Listbox5.size()

		if (a > 0):
			contents = Listbox5.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox4.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox4.get(i).startswith(' - ') == True):
					Listbox4.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox4.itemconfig(0, background = '#B4E6FF')
			if (Listbox5clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox5.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox4.get(i).startswith('******')) == False):
							Listbox4.itemconfig(i, background = 'green')
						else:
							break
					Listbox4clear = True

			else:
				Listbox5clear = False				

			Listbox5.delete(0, END)


	def listbox1done():
		global Listbox0clear, Listbox1clear, Listbox2clear, Listbox3clear, Listbox4clear, Listbox5clear
		Listbox1.delete(0, END)
		Listbox1clear = False

		a = Listbox2.size()

		if (a > 0):
			contents = Listbox2.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox1.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox1.get(i).startswith(' - ') == True):
					Listbox1.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox1.itemconfig(0, background = '#B4E6FF')
			if (Listbox2clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox2.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox1.get(i).startswith('******')) == False):
							Listbox1.itemconfig(i, background = 'green')
						else:
							break
					Listbox1clear = True

			else:
				Listbox2clear = False				

			Listbox2.delete(0, END)

		a = Listbox3.size()

		if (a > 0):
			contents = Listbox3.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox2.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox2.get(i).startswith(' - ') == True):
					Listbox2.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox2.itemconfig(0, background = '#B4E6FF')
			if (Listbox3clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox3.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox2.get(i).startswith('******')) == False):
							Listbox2.itemconfig(i, background = 'green')
						else:
							break
					Listbox2clear = True

			else:
				Listbox3clear = False				

			Listbox3.delete(0, END)

		a = Listbox4.size()

		if (a > 0):
			contents = Listbox4.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox3.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox3.get(i).startswith(' - ') == True):
					Listbox3.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox3.itemconfig(0, background = '#B4E6FF')
			if (Listbox4clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox4.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox3.get(i).startswith('******')) == False):
							Listbox3.itemconfig(i, background = 'green')
						else:
							break
					Listbox3clear = True

			else:
				Listbox4clear = False				

			Listbox4.delete(0, END)

		a = Listbox5.size()

		if (a > 0):
			contents = Listbox5.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox4.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox4.get(i).startswith(' - ') == True):
					Listbox4.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox4.itemconfig(0, background = '#B4E6FF')
			if (Listbox5clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox5.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox4.get(i).startswith('******')) == False):
							Listbox4.itemconfig(i, background = 'green')
						else:
							break
					Listbox4clear = True

			else:
				Listbox5clear = False				

			Listbox5.delete(0, END)



	def listbox2done():
		global Listbox0clear, Listbox1clear, Listbox2clear, Listbox3clear, Listbox4clear, Listbox5clear
		Listbox2.delete(0, END)
		Listbox2clear = False

		a = Listbox3.size()

		if (a > 0):
			contents = Listbox3.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox2.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox2.get(i).startswith(' - ') == True):
					Listbox2.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox2.itemconfig(0, background = '#B4E6FF')
			if (Listbox3clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox3.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox2.get(i).startswith('******')) == False):
							Listbox2.itemconfig(i, background = 'green')
						else:
							break
					Listbox2clear = True

			else:
				Listbox3clear = False				

			Listbox3.delete(0, END)

		a = Listbox4.size()

		if (a > 0):
			contents = Listbox4.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox3.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox3.get(i).startswith(' - ') == True):
					Listbox3.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox3.itemconfig(0, background = '#B4E6FF')
			if (Listbox4clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox4.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox3.get(i).startswith('******')) == False):
							Listbox3.itemconfig(i, background = 'green')
						else:
							break
					Listbox3clear = True

			else:
				Listbox4clear = False				

			Listbox4.delete(0, END)

		a = Listbox5.size()

		if (a > 0):
			contents = Listbox5.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox4.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox4.get(i).startswith(' - ') == True):
					Listbox4.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox4.itemconfig(0, background = '#B4E6FF')
			if (Listbox5clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox5.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox4.get(i).startswith('******')) == False):
							Listbox4.itemconfig(i, background = 'green')
						else:
							break
					Listbox4clear = True

			else:
				Listbox5clear = False				

			Listbox5.delete(0, END)

	def listbox3done():
		global Listbox0clear, Listbox1clear, Listbox2clear, Listbox3clear, Listbox4clear, Listbox5clear
		Listbox3.delete(0, END)
		Listbox3clear = False

		a = Listbox4.size()

		if (a > 0):
			contents = Listbox4.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox3.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox3.get(i).startswith(' - ') == True):
					Listbox3.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox3.itemconfig(0, background = '#B4E6FF')
			if (Listbox4clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox4.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox3.get(i).startswith('******')) == False):
							Listbox3.itemconfig(i, background = 'green')
						else:
							break
					Listbox3clear = True

			else:
				Listbox4clear = False				

			Listbox4.delete(0, END)

		a = Listbox5.size()

		if (a > 0):
			contents = Listbox5.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox4.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox4.get(i).startswith(' - ') == True):
					Listbox4.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox4.itemconfig(0, background = '#B4E6FF')
			if (Listbox5clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox5.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox4.get(i).startswith('******')) == False):
							Listbox4.itemconfig(i, background = 'green')
						else:
							break
					Listbox4clear = True

			else:
				Listbox5clear = False				

			Listbox5.delete(0, END)	


	def listbox4done():
		global Listbox0clear, Listbox1clear, Listbox2clear, Listbox3clear, Listbox4clear, Listbox5clear
		Listbox4.delete(0, END)
		Listbox4clear = False

		a = Listbox5.size()

		if (a > 0):
			contents = Listbox5.get(0, END)			#Call contents of next Listbox

			for i in range(0, a):
				Listbox4.insert(i, contents[i])		#Insert contents of next listbox into previous listbox
				if (Listbox4.get(i).startswith(' - ') == True):
					Listbox4.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox4.itemconfig(0, background = '#B4E6FF')
			if (Listbox5clear == True): 				#Checks to see if next listbox has already been cleared
				a = Listbox5.size()
				if (a > 0):
					for i in range(1, a):
						if ((Listbox4.get(i).startswith('******')) == False):
							Listbox4.itemconfig(i, background = 'green')
						else:
							break
					Listbox4clear = True

			else:
				Listbox5clear = False				

			Listbox5.delete(0, END)	


	def listbox5done():
		global pendingorder1, pendingorder2, pendingorder3, pendingorder4, pendingorder5, pendingorder6
		Listbox5.delete(0, END)
		Listbox5clear = False

		if (pendingorder1[0] != ''):
			po1l = len(pendingorder1)	#Retreives length of list
			for i in range(0, po1l):
				Listbox5.insert(i, pendingorder1[i])
				if (Listbox5.get(i).startswith(' - ') == True):
					Listbox5.itemconfig(i, background = 'yellow', foreground = 'red')
			Listbox5.itemconfig(0, background = '#B4E6FF')

			if (pendingorder2[0] != ""):	#Loop checks if next list has contents, then transfers all the data to the previous list, then every subsequent loop checks the subsequent lists
				x = len(pendingorder2)
				for i in range(0, (x-1)): 		#Erases list
					pendingorder1.pop()
				for i in range(0, x):
					pendingorder1[i] = pendingorder2[i] 	#Appends list identically
				if (pendingorder3[0] != ""):
					x = len(pendingorder3)
					for i in range(0, (x-1)):
						pendingorder2.pop()
					for i in range(0, x):
						pendingorder2[i] = pendingorder3[i]
					if (pendingorder4[0] != ""):
						x = len(pendingorder4)
						for i in range(0, (x-1)):
							pendingorder3.pop()
						for i in range(0, x):
							pendingorder3[i] = pendingorder4[i]
						if (pendingorder5[0] != ""):
							x = len(pendingorder5)
							for i in range(0, (x-1)):
								pendingorder4.pop()
							for i in range(0, x):
								pendingorder4[i] = pendingorder5[i]
							if (pendingorder6[0] != ""):
								x = len(pendingorder6)
								for i in range(0, (x-1)):
									pendingorder5.pop()
								for i in range(0, x):
									pendingorder5[i] = pendingorder6[i]
								for i in range(0, (x-1)):
									pendingorder6.pop()
								pendingorder6 = [""]
			else:
				x = len(pendingorder1)
				x = x - 1
				for i in range(0, x):
					pendingorder1.pop()
				pendingorder1 = [""]


		elif (pendingorder2[0] != ''):
			po2l = len(pendingorder2)	#Retreives length of list
			for i in range(0, po2l):
				Listbox5.insert(i, pendingorder2[i])
				if (Listbox5.get(i).startswith(' - ') == True):
					Listbox5.itemconfig(i, background = 'yellow', foreground = 'red')
			if (pendingorder3[0] != ""):
				x = len(pendingorder3)
				for i in range(0, x):
					pendingorder2.pop(i)
				for i in range(0, x):
					pendingorder2[i] = pendingorder3[i]
				if (pendingorder4[0] != ""):
					x = len(pendingorder4)
					for i in range(0, x):
						pendingorder3.pop(i)
					for i in range(0, x):
						pendingorder3[i] = pendingorder4[i]
					if (pendingorder5[0] != ""):
						x = len(pendingorder5)
						for i in range(0, x):
							pendingorder4.pop(i)
						for i in range(0, x):
							pendingorder4[i] = pendingorder5[i]
						if (pendingorder6[0] != ""):
							x = len(pendingorder6)
							for i in range(0, x):
								pendingorder5.pop(i)
							for i in range(0, x):
								pendingorder5[i] = pendingorder6[i]
							for i in range(0, x):
								pendingorder6.pop(i)
							pendingorder6 = [""]


		elif (pendingorder3[0] != ''):
			po3l = len(pendingorder3)	#Retreives length of list
			for i in range(0, po3l):
				Listbox5.insert(i, pendingorder3[i])
				if (Listbox5.get(i).startswith(' - ') == True):
					Listbox5.itemconfig(i, background = 'yellow', foreground = 'red')

			if (pendingorder4[0] != ""):
				x = len(pendingorder4)
				for i in range(0, x):
					pendingorder3.pop(i)
				for i in range(0, x):
					pendingorder3[i] = pendingorder4[i]
				if (pendingorder5[0] != ""):
					x = len(pendingorder5)
					for i in range(0, x):
						pendingorder4.pop(i)
					for i in range(0, x):
						pendingorder4[i] = pendingorder5[i]
					if (pendingorder6[0] != ""):
						x = len(pendingorder6)
						for i in range(0, x):
							pendingorder5.pop(i)
						for i in range(0, x):
							pendingorder5[i] = pendingorder6[i]
						for i in range(0, x):
							pendingorder6.pop(i)
						pendingorder6 = [""]


		elif (pendingorder4[0] != ''):
			po4l = len(pendingorder4)	#Retreives length of list
			for i in range(0, po4l):
				Listbox5.insert(i, pendingorder4[i])
				if (Listbox5.get(i).startswith(' - ') == True):
					Listbox5.itemconfig(i, background = 'yellow', foreground = 'red')

			if (pendingorder5[0] != ""):
				x = len(pendingorder5)
				for i in range(0, x):
					pendingorder4.pop(i)
				for i in range(0, x):
					pendingorder4[i] = pendingorder5[i]
				if (pendingorder6[0] != ""):
					x = len(pendingorder6)
					for i in range(0, x):
						pendingorder5.pop(i)
					for i in range(0, x):
						pendingorder5[i] = pendingorder6[i]
					for i in range(0, x):
						pendingorder6.pop(i)
					pendingorder6 = [""]


		elif (pendingorder5[0] != ''):
			po5l = len(pendingorder5)	#Retreives length of list
			for i in range(0, po5l):
				Listbox5.insert(i, pendingorder5[i])
				if (Listbox5.get(i).startswith(' - ') == True):
					Listbox5.itemconfig(i, background = 'yellow', foreground = 'red')

			if (pendingorder6[0] != ""):
				x = len(pendingorder6)
				for i in range(0, x):
					pendingorder5.pop(i)
				for i in range(0, x):
					pendingorder5[i] = pendingorder6[i]
				for i in range(0, x):
					pendingorder6.pop(i)
				pendingorder6 = [""]


		elif (pendingorder6[0] != ''):
			po6l = len(pendingorder6)	#Retreives length of list
			for i in range(0, po6l):
				Listbox5.insert(i, pendingorder6[i])
				if (Listbox5.get(i).startswith(' - ') == True):
					Listbox5.itemconfig(i, background = 'yellow', foreground = 'red')

			x = len(pendingorder6)
			for i in range(0, x):
				pendingorder6.pop(i)
			pendingorder6 = [""]

		else:
			Listbox5.delete(0, END)


	Done0 = Button(ButtonFrame0, text = 'DONE', command = listbox0done)
	Done0.grid(column = 2, row = 0, sticky = E)

	Done1 = Button(ButtonFrame1, text = 'DONE', command = listbox1done)
	Done1.grid(column = 2, row = 0, sticky = E)

	Done2 = Button(ButtonFrame2, text = 'DONE', command = listbox2done)
	Done2.grid(column = 2, row = 0, sticky = E)

	Done3 = Button(ButtonFrame3, text = 'DONE', command = listbox3done)
	Done3.grid(column = 2, row = 0, sticky = E)

	Done4 = Button(ButtonFrame4, text = 'DONE', command = listbox4done)
	Done4.grid(column = 2, row = 0, sticky = E)

	Done5 = Button(ButtonFrame5, text = 'DONE', command = listbox5done)
	Done5.grid(column = 2, row = 0, sticky = E)

	########################################################



	kitchen.mainloop()


def main():
	root = Tk()
	root.tk.call('tk', 'scaling', 16.0) #Function for users with 4k screen

	Frame0 = Frame(root, borderwidth = 2)
	Frame0.grid(column = 0, row = 0)

	button1 = Button(Frame0, text = "Ordering System", command = OrderGUI)
	button1.grid(column = 0, row = 0, columnspan = 3)

	button2 = Button(Frame0, text = "Kitchen System", command = KitchenGUI)
	button2.grid(column = 0, row = 1, columnspan = 3)

	button3 = Button(Frame0, text = "Stock Control System", command = StockControl)
	button3.grid(column = 0, row = 2, columnspan = 3)

	root.title('The Lamb Inn Ordering System')
	root.mainloop()


main() 
