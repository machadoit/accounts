2 REM        *********************************************
5 REM        *This is Cortijo Rosario Package 9/4/2009 *
7 REM        *********************************************
9 LET mess$ = "Happy new year!!!!"
 LET mess1$ = "Nearly Chrimbo!!!"
 IF VAL(LEFT$(realdate$, 2)) = 12 THEN LET mess$ = mess1$
LET room1$ = "Room 1"
LET room2$ = "Room 2"
LET room3$ = "Room 3"
LET room4$ = "Room 4"
LET room5$ = "Room 5"
LET room6$ = "Room 7"
LET room7$ = "Room 7"
LET room8$ = "Room 8"
LET room9$ = "Room 9"
LET room10$ = "Room 10"
LET room11$ = "Room 11"

LET trans0$ = "B/Forward"

LET trans1$ = "Food"
LET trans111$ = "Daily Food"
LET trans112$ = "Bread"
LET trans113$ = "Fish"
LET trans114$ = "Frozen Food"
LET trans115$ = "O.J./Milk"
LET trans116$ = "Wine"
LET trans117$ = "Mineral Water"

LET trans2$ = "L/P Cost"
LET trans201$ = "L/P Juan Fuel"
LET trans202$ = "L/P Guide"
LET trans203$ = "L/P Restaurant"
LET trans204$ = "L/P Laundry"

LET trans3$ = "Wages"
LET trans301$ = "Staff Wages"
LET trans302$ = "Temp Wages"
LET trans303$ = "Soc. Sec."

LET trans4$ = "Minibus"
LET trans401$ = "Income"
LET trans402$ = "Fuel"
LET trans403$ = "Maintenance"
LET trans404$ = "Loan Payment"
LET trans405$ = "Insurance"
LET trans406$ = "Legal"

LET trans5$ = "Income"
LET trans501$ = "Exodus"
LET trans502$ = "Local Payment"
LET trans503$ = "Lunches"
LET trans504$ = "Payphone"
LET trans505$ = "Ice Cream"
LET trans506$ = "Postcards"
LET trans507$ = "VAT"
LET trans508$ = "Apartments"
LET trans509$ = "Bar"
LET trans510$ = "Jerez Horses"
LET trans511$ = "Jerez Bodega"

LET trans6$ = "Car"
LET trans601$ = "Car Fuel"
LET trans602$ = "Maintenance"
LET trans603$ = "Tax"

LET trans7$ = "Utils"
LET trans701$ = "Insurance"
LET trans702$ = "Phone"
LET trans703$ = "Electricity"
LET trans704$ = "Gas"
LET trans705$ = "Heating Oil"
LET trans706$ = "Logs"
LET trans707$ = "Refuse"




LET trans8$ = "CookBk"
LET trans801$ = "Sales"
LET trans802$ = "Book Cost"
LET trans803$ = "Postage"

LET trans9$ = "Maint"
LET trans901$ = "House Guest Room"
LET trans902$ = "House Kitchen"
LET trans903$ = "House Bathroom"
LET trans904$ = "House Exterior"
LET trans905$ = "House Pool One off"
LET trans906$ = "Gardens One off"
LET trans907$ = "Studios One off"
LET trans908$ = "Cortijo Bedrooms One off"
LET trans909$ = "Cortijo Office One off"
LET trans910$ = "Cortijo Pool One off"
LET trans911$ = "Cortijo Kitchen One off"
LET trans912$ = "Cortijo Public Rooms One off"
LET trans913$ = "Cortijo Exterior One off"

LET trans951$ = "House Bedrooms"
LET trans952$ = "House Kitchen"
LET trans953$ = "House Bathroom"
LET trans954$ = "House Exterior"
LET trans955$ = "House Pool"
LET trans956$ = "Gardens"
LET trans957$ = "Studios"
LET trans958$ = "Cortijo Bedrooms"
LET trans959$ = "Cortijo Office"
LET trans960$ = "Cortijo Pool"
LET trans961$ = "Cortijo Kitchen"
LET trans962$ = "Cortijo Public Rooms"
LET trans963$ = "Cortijo Exterior"

LET trans10$ = "Misc."
LET trans1001$ = "Equipment"
LET trans1002$ = "Legal Fees"
LET trans1003$ = "Pool Loan"
LET trans1004$ = "Sundries"
LET trans1005$ = "Cleaning Materials"
LET trans1006$ = ""
LET trans1007$ = "Advertising "
LET trans1008$ = "Flamenco"
LET trans1009$ = ""

LET trans11$ = "      "











LET clear$ = " "
LET realdate$ = MID$(DATE$, 4, 2) + "-" + LEFT$(DATE$, 2) + "-" + RIGHT$(DATE$, 4)
LET pass$ = "pass"
LET File$ = "trans"
LET FILESO$ = "sofile"
LET OLDFILE$ = "OLDLEDG"
LET printfile$ = "Print.txt"
LET title$ = "Cortijo Rosario Accounts System                                       "
LET shorttitle$ = LEFT$(title$, 32)
LET account = 1
LET Master$ = "W.G.Brett"
LET Mistress$ = "A.C.Brett"
LET incometype1$ = "Cash"
LET incometype2$ = "Cheq"
LET incometype3$ = "Xfer"
LET incometype4$ = "D.D"
LET incometype5$ = "Card"
LET incometype6$ = "Coop"
LET incometype76$ = "IVA"

GOSUB 4080: REM setup balance and refno at start


30 GOSUB 900: REM Handle master menu
40 IF menu = 1 OR menu > 99 AND menu < 200 THEN GOSUB 4600: REM Display Food
   IF menu = 2 OR menu > 199 AND menu < 300 THEN GOSUB 4600: REM Display Drinks
70 IF menu = 3 OR menu > 299 AND menu < 400 THEN GOSUB 4600: REM display Nothing
   IF menu = 4 OR menu > 399 AND menu < 500 THEN GOSUB 4600: REM display Minibus
   IF menu = 5 OR menu > 499 AND menu < 600 THEN GOSUB 4600: REM display Income
   IF menu = 6 OR menu > 599 AND menu < 700 THEN GOSUB 4600: REM display Vehicles
   IF menu = 7 OR menu > 699 AND menu < 800 THEN GOSUB 4600: REM display Utilities Costs
   IF menu = 8 OR menu > 799 AND menu < 900 THEN GOSUB 4600: REM display Water costs
   IF menu = 9 OR menu > 899 AND menu < 1000 THEN GOSUB 4600: REM display House Maint.
   IF menu = 10 OR menu > 999 AND menu < 1100 THEN GOSUB 4600: REM display Misc. costs
   IF menu = 13 THEN GOSUB 270: REM enter new transactions
   IF menu = 14 THEN GOSUB 270: REM display complete file
   IF menu = 15 THEN GOSUB 1100: REM Choose Account
   IF menu = 16 THEN GOSUB 7500: REM Control Standing Orders
   IF menu = 17 THEN GOSUB 5000: REM Search for record
   REM IF menu = 18 THEN GOSUB 4600: REM Show IVA totals
   IF menu = 19 THEN GOSUB 6100: REM show month totals
   IF menu = 20 THEN GOSUB 6100: REM show year totals
   IF menu = 99 THEN GOTO 230: REM close system
   IF menu = 41661 THEN GOSUB killiva
130 GOTO 30















killiva: CLOSE #1
CLOSE #3
OPEN "I", 1, File$

OPEN "o", 3, "ivatrans"
 WHILE NOT (EOF(1))
 INPUT #1, dat$, desc$, trans$, debit, Credit, incometype, refno, account$, IVA
 LET IVA = 0
 WRITE #3, dat$, desc$, trans$, debit, Credit, incometype, refno, account$, IVA
 WEND
 CLOSE #1

 CLOSE #3
 RETURN

REM: Read transaction File
readtr: LET accflag$ = ""
	INPUT #1, dat$, desc$, trans$, debit, Credit, incometype, refno, account$, IVA
	IF nonrec$ = "Y" AND LEFT$(trans$, 2) = "90" THEN LET accflag$ = "": RETURN
	IF nonrec$ = "Y" AND LEFT$(trans$, 2) = "91" THEN LET accflag$ = "": RETURN
	IF account = 0 THEN LET accflag$ = "Y": RETURN
	IF account = VAL(account$) OR menu = 18 THEN LET accflag$ = "Y": RETURN
	RETURN

REM: Write Transaction File
writetr: IF wmflag$ = "Y" THEN GOTO addrecs
	 WRITE #1, dat$, desc$, trans$, debit, Credit, incometype, refbak, account$, IVA
	 IF wm$ = "w" THEN LET weekno = weekno + 1
	 IF weekno > 53 THEN LET weekno = 0
	 IF wm$ = "m" THEN GOSUB chgemon
	 LET wmcount = wmcount - 1
	 IF wmcount > 0 THEN GOTO writetr
	 
	 RETURN

chgemon:  LET wmmon = VAL(MID$(dat$, 4, 2))
	  LET wmyr = VAL(RIGHT$(dat$, 4))
	  LET wmday = VAL(LEFT$(dat$, 2))
	  LET wmmon = wmmon + 1
	  IF wmmon > 12 THEN LET wmmon = 1: LET wmyr = wmyr + 1
	  LET dat$ = STR$(wmday) + STR$(wmmon) + STR$(wmyr)
	    RETURN

 LET a$ = MID$(dat$, b, 1)
 IF a$ = "/" OR a$ = "-" OR a$ = "" THEN RETURN
 LET b = b + 1
 GOTO 1500

	  RETURN




addrecs:  IF wm$ = "m" GOTO addmonth

addweeks: IF Credit > 0 THEN LET Credit = Credit / wmcount
	  IF debit > 0 THEN LET debit = debit / wmcount
	  LET wmflag$ = ""
	  GOSUB writetr
	  RETURN

addmonth: IF Credit > 0 THEN LET Credit = Credit / wmcount
	  IF debit > 0 THEN LET debit = debit / wmcount
	  LET wmflag$ = ""
	  GOSUB writetr

	  RETURN


REM: Read Standing Order File
readso: INPUT #2, SODAY$, desc$, debit, SOMONTH, sofreq, sotrans$, soaccount$
RETURN

REM: Write Standing Order File
writeso: WRITE #1, SODAY$, SODESC$, sodebit, SOMONTH, sofreq, soaccount$
RETURN

230 CLS
240 SYSTEM
250
260
270 GOSUB 490: REM display headings for file display
280 GOSUB 570: REM display file
290 RETURN
300
310
320
330
340 REM add new records to trans file
350 OPEN "a", 1, File$
360 LET l = 3
370 GOSUB 490: REM do titles
380 GOSUB entE
390 GOSUB 2290
400 LOCATE 23, 1: PRINT "                                                            "
410 IF dat$ = "e" OR dat$ = "E" GOTO 30
420 GOSUB writetr
430 IF l >= 18 GOTO 360
440 GOTO 380
450
460
470
480
490 CLS
500 PRINT title$; realdate$
501 IF print$ <> "Y" THEN GOTO 502
    PRINT #4, shorttitle$;
    IF menu < 13 THEN PRINT #4, TAB(35); "Transaction Type:"; TAB(53); transcode$;
    PRINT #4, TAB(71); realdate$
502 GOSUB 6050
503 IF menu < 13 THEN LOCATE 1, 35: PRINT "Transaction Type:"; TAB(53); transcode$;
    IF print$ <> "Y" GOTO 505
    PRINT #4, "Date       Ref    Wk Trans.  Type    Credit     Debit   Acc   Description"
505 PRINT "Date       Ref  Trans.   Type   Credit      Debit         Acc   Description"
510 REM IF menu = 13 THEN LOCATE 2, 1: PRINT "Date       Ref    Wk Trans.  Type    Credit     Debit   Acc   Description"
520 RETURN
530
540
550
560
570 REM "DISPLAY RECORDS "
580 LET lcount = 0
590 LET SCOUNT = 0: REM screen count=20 line counts
600 OPEN "I", 1, File$
610 WHILE NOT (EOF(1))
620 GOSUB readtr
625 IF accflag$ <> "Y" THEN GOTO 750
630 IF pcount = 0 GOTO 665
640 IF SCOUNT <> pcount - 1 THEN GOTO 690
650 LET pcount = 0
660 LET more$ = "M"
665 GOSUB 6000
670 LOCATE lcount + 3, 1: PRINT dat$; TAB(11); refno; TAB(17); Translate$; TAB(26); incometype$;
672 LOCATE lcount + 3, 30: PRINT USING "######.##"; Credit;
	PRINT USING "#######.##"; TAB(40); debit;
      REM  PRINT USING "#####.##"; TAB(50); IVA
675 LOCATE lcount + 3, 59: PRINT who$; TAB(65); desc$
680 REM:PRINT "                                                                                "
690 lcount = lcount + 1
700 IF lcount < 19 GOTO 750
710 GOSUB 1740
720 IF more$ = "M" GOTO 750
730 IF more$ = "P" GOTO 750
740 GOTO 800
750 WEND
752 IF menu = 13 THEN LET l = lcount + 3
760 IF lcount = 20 GOTO 792
770 PRINT "                                                                               "
780 lcount = lcount + 1
790 GOTO 760
792 CLOSE #1
800 LOCATE 1, 40: PRINT "Page No."; SCOUNT + 1
802 IF menu <> 13 THEN GOTO 810
805 OPEN "a", 1, File$
806 GOTO 380
810 LOCATE 23, 1: INPUT "Enter (E)nd to exit", more$
812 IF more$ = "e" THEN LET more$ = "E"
815 IF more$ <> "E" GOTO 810
820 LET SCOUNT = 0
830 LET pcount = 0
850 RETURN
860
870
880
890
900  CLS
910  CLOSE #1
     IF print$ = "Y" THEN PRINT #4, CHR$(12)
     LET print$ = "N"
     CLOSE #4
     LET prcount = 0
920  LOCATE 1, 1: PRINT title$; realdate$
930  LOCATE 3, 38: PRINT "MENU"
940  REM LOCATE 4, 38: PRINT "----"
955  LOCATE 4, 10: PRINT " 1. Show all Food costs"
965  LOCATE 5, 10: PRINT " 2. Show all Local Payment Costs"
970  LOCATE 6, 10: PRINT " 3. Show all Wages Costs"
980  LOCATE 7, 10: PRINT " 4. Show all Minibus"
990  LOCATE 8, 10: PRINT " 5. Show all Income"
1000 LOCATE 9, 10: PRINT " 6. Show all Vehicles Cost Items"
1001 LOCATE 10, 10: PRINT " 7. Show all Utilities Costs"
1003 LOCATE 11, 10: PRINT " 8. Show all Cook Book"
     LOCATE 12, 10: PRINT " 9. Show all House Maint."
     LOCATE 13, 10: PRINT "10. Show all Misc. costs."
     LOCATE 14, 10: PRINT "13. Enter New Transactions"
     LOCATE 15, 10: PRINT "14. Display Complete File"
     LOCATE 16, 10: PRINT "15. Choose Account"
1004 LOCATE 17, 10: PRINT "16. Control Standing Orders"
1005 LOCATE 18, 10: PRINT "17. Search for Transaction"
REM 1018 LOCATE 19, 10: PRINT "18. Show IVA Totals"
1019 LOCATE 19, 10: PRINT "19. Show MonthlyTotals"
1920 LOCATE 20, 10: PRINT "20. Show Yearly Totals"
1030 LOCATE 21, 10: PRINT "99. End program"
1040 LOCATE 23, 10: INPUT "Select:  ", menu
1045 OPEN "a", 4, printfile$
     GOSUB clr2123
1050 RETURN

1100 LET nonrec$ = ""
     LOCATE 22, 1: PRINT "Enter: (1) for Cortijo,(2) for Coach House, (3) for All"
     LOCATE 23, 1: INPUT "         4,5,6 for without one off costs  ", account
     IF account >= 4 THEN LET nonrec$ = "Y": LET account = account - 3
     IF account >= 3 THEN LET account = 0
     RETURN

1500 LET a$ = MID$(dat$, b, 1)
1510 IF a$ = "/" OR a$ = "-" OR a$ = "" THEN RETURN
1530 LET b = b + 1
1540 GOTO 1500

1740 REM ask if more in display complete file
1750 LET lcount = 0
1760 LET SCOUNT = SCOUNT + 1
1770 LOCATE 1, 40: PRINT "Page No."; SCOUNT
1780 IF more$ = "P" GOTO 1850
1785 IF menu <> 13 THEN GOTO 1790
1787 LET more$ = "P": LET pcount = 0: GOTO 1850: REM set for enter at end of file
1790 LOCATE 23, 1: INPUT "Select M for more,P for page or ENTER to end ", more$
1800 GOSUB 4360
1810 IF more$ = "m" THEN LET more$ = "M"
1820 IF more$ = "p" THEN LET more$ = "P"
1830 IF more$ <> "M" AND more$ <> "P" AND more$ <> "" THEN GOTO 1790
1840 IF more$ = "P" THEN LOCATE 23, 1: INPUT "Enter page number required.(0=last page)", pcount
1850 GOSUB 4360
1860 RETURN

2210
2220 CLS
2230 PRINT title$; realdate$
2231 LOCATE 1, 27: PRINT "Balance B/Fwd=Euro"; USING "#####.##"; Spare
2240 PRINT "  Date       Ref.   Week Transaction    Credit     Debit  Acc   Description    "
2250 RETURN
2260
2270
2280 REM get new record
2290 LET debit = 0: LET debit$ = " "
2300 LET Credit = 0: LET Credit$ = " "
2304 LET incometype = 0
2305 GOSUB entE
2306 LOCATE l, 1: PRINT "                                                                               "
2307 LOCATE l, 1: PRINT realdate$
2310 LOCATE l, 1: INPUT "", dat$
2312 IF LEN(dat$) = 10 GOTO 2324
2314 IF dat$ = "E" OR dat$ = "e" THEN RETURN
2316 LET dat$ = LEFT$(dat$, LEN(dat$)) + RIGHT$(realdate$, (10 - LEN(dat$)))
2320 IF dat$ = "" THEN LET dat$ = realdate$: GOTO 2325
       
2324 GOSUB 5900: REM validate date entered
2325 IF flag$ = "E" GOTO 2310
2326 GOSUB clr2123
2327 REM:LOCATE l, 20: INPUT "", weekno
2328 REM:LOCATE 23, 1: PRINT "                  Depress CTRL/D to Restart Line"
     REM:IF weekno > 52 THEN GOTO 2327
     

2332 LOCATE 21, 1: PRINT "Enter 1=Food 2=L/P Costs 3=Wages 4=Minibus 5=Income 6=Vehicles"
2333 LOCATE 22, 1: PRINT "      7=Utilities 8=Cook Book 9=House Maint. 10=Misc."
2335 LOCATE 23, 1: PRINT "                  Depress CTRL/D to Restart Line"
2340 LOCATE l, 17: INPUT "", trans$
2345 GOSUB clr2123
2350 IF trans$ = "" GOTO 2280 ELSE GOSUB 6000: REM translate transaction code
2355 LOCATE l, 17: PRINT Translate$
2360 REM LOCATE 23, 1: PRINT "                                                                               "
    
     GOSUB vis: REM:Ask for incometype
     IF LEFT$(trans$, 1) = "5" OR LEFT$(trans$, 1) = "8" OR trans$ = "401" THEN GOTO 2410: REM Credit for income
    
2370 LOCATE l, 42: INPUT "", debit
     LOCATE l, 42: PRINT USING "#####.##"; debit
     LET debit$ = STR$(debit)
     IF debit <> 0 GOTO 2420

2410 LOCATE l, 31: INPUT "", Credit
     LOCATE l, 31: PRINT USING "#####.##"; Credit
     LET Credit$ = STR$(Credit)
     IF Credit = 0 GOTO 2370
     GOTO 2430

2420 REM LOCATE l, 51: INPUT "", IVA
     REM LOCATE l, 51: PRINT USING "####.##"; IVA
     LET IVA$ = STR$(IVA)
    



2430 IF wmflag$ <> "Y" THEN GOTO recurr
2431 LOCATE 23, 1: PRINT "Depress CTRL/D to Restart Line"
     LOCATE l, 65: INPUT "", desc$
     IF desc$ = "" THEN GOSUB clr223: GOTO 2280





2432 LOCATE 22, 1: IF LEFT$(trans$, 1) <> "5" THEN PRINT "Enter (1) for Cortijo,(2) for Coach House " ELSE PRINT "Depress CTRL/D to restart line entry."
2435 LOCATE l, 59: INPUT "", account$
     
     IF account$ = "" GOTO 2280
     IF account$ = "1" THEN LOCATE l, 59: PRINT "Hotel"
     IF account$ = "2" THEN LOCATE l, 59: PRINT "House"
     GOSUB clr2123: GOTO 2440
     GOTO 2435
    
2440 IF LEFT$(trans$, 1) <> "3" AND LEFT$(trans$, 1) <> "5" OR LEFT$(trans$, 1) <> "" THEN GOTO nom
     IF accounts$ <> "1" OR desc$ <> "2" THEN GOTO 2435
     GOSUB clr2123
     GOSUB 6000: REM translate who
     LOCATE l, 58: PRINT who$

nom: LET refno = refno + 1
     LET refbak = refno
     LOCATE l, 11: PRINT refno
     LOCATE l, 12: INPUT "", ref
     IF ref = 0 THEN GOTO 2500
     LET refbak = ref
     LET refno = refno - 1
2500 LET l = l + 1
2505 LET more$ = "": LET pcount = 0
2510 RETURN



recurr: IF debit = 999 THEN GOTO time
	IF Credit = 999 THEN GOTO time
	GOTO 2431

time:   LOCATE 22, 1: PRINT "Enter:(W) for Week, (M) for Month"
	LOCATE 23, 30: INPUT "", wm$
	LOCATE 22, 1: PRINT "Enter How Many                   "
	LOCATE 23, 30: INPUT "", wmcount
	LET wmflag$ = "Y"
	GOSUB clr2123
	GOTO 2370






vis: LOCATE 23, 1: PRINT "Enter (1) Cash, (2) Cheque, (3) Xfer, (4) D.Debit, (5) Card, (6) Co-op"
     LOCATE l, 26: INPUT "", incometype
     IF incometype > 6 OR incometype < 1 THEN GOTO vis
     GOSUB 6000
     LOCATE l, 26: PRINT incometype$
     GOSUB clr2123
     RETURN
    
2540 REM display records
2550 LOCATE 3, 1
2560 LET lcount = 0
2570 LET SCOUNT = 0: REM screen count=20 line counts
2580 OPEN "I", 1, File$
2590 WHILE NOT (EOF(1))
2600 GOSUB readtr
2605 REM trans$ = trans$ - menu
2612 IF LEFT$(trans$, 1) = "0" GOTO 2620 ELSE GOTO 2740: REM display record type selected
2620 LOCATE lcount + 3, 1: PRINT dat$; TAB(20); trans$; TAB(35); USING "#####.##"; Credit; TAB(45); debit; TAB(55); TAB(70); : PRINT desc$
2630 PRINT "                                                                               "
2640 lcount = lcount + 1
2650 IF lcount < 18 GOTO 2740
2660 LET lcount = 0
2670 LOCATE 23, 1: INPUT "Select C for checking,M for more,Enter for end"; more$
2680 IF more$ = "c" THEN LET more$ = "C"
2690 IF more$ = "C" GOTO 2790
2700 LET SCOUNT = SCOUNT + 1
2710 IF more$ = "m" THEN LET more$ = "M"
2720 IF more$ <> "M" THEN GOTO 30
2730 LOCATE 3, 1
2740 WEND
2750 IF lcount = 18 GOTO 2790
2760 PRINT "                                                                               "
2770 lcount = lcount + 1
2780 GOTO 2750
2790 LOCATE 23, 1
2800 PRINT "                                                                             "
2810 CLOSE #1
2820 RETURN

2840 REM Clear all data on screen
2850 LOCATE 3
2860 RETURN
2870 FOR CLINE = 3 TO 23
2880 PRINT "                                                                             "
2890 NEXT CLINE
2900 LOCATE 3
2910 RETURN

REM:2940 REM rebalance file
2950 LET oldbalance = 0
2960 OPEN "I", 1, File$
2970 OPEN "o", 2, "NEWCATER"
2980 WHILE NOT (EOF(1))
2990 GOSUB readtr
3020 LET Spare = oldbalance + Credit - debit
3025 LET Spare = Spare * 100: LET Spare = CLNG(Spare): LET Spare = Spare / 100
3030 LET oldbalance = Spare
3050 WRITE #2, dat$, desc$, trans$, debit, Credit, incometype, refno, account$, weekno
3060 WEND
3070 CLOSE #1
3080 CLOSE #2
3090 KILL File$
3100 NAME "NEWCATER" AS File$
3110 RETURN



4020 LOCATE 23, 1: INPUT "Depress ENTER to end", more$
4030 CLOSE #1
4070 RETURN



4080 OPEN "i", 1, File$
4090 WHILE NOT (EOF(1))
4100 GOSUB readtr
4110 WEND
4120 CLOSE #1
4130
     RETURN
4140
4150
4350
4360 GOSUB clrall
4370 LOCATE 3
4380 RETURN
4385
4387
4390 LOCATE 23, 1: INPUT "Password                "; PASSWORD$
4400 IF PASSWORD$ = pass$ THEN GOSUB 4360
4405 LET statement = 0
4410 IF PASSWORD$ = pass$ GOTO 4430
4420 RETURN
4430 OPEN "I", 1, File$
4431 WHILE NOT (EOF(1))
4432 GOSUB readtr
4434 WEND
4435 CLOSE #1
4439 LET oldbalance = statement
4440 OPEN "I", 1, File$
4450 OPEN "o", 2, "NEWTRANS"
4451 LET dat$ = "1/1/05"
4452 LET Credit = statement
4453 LET debit = 0
4454 LET trans$ = "B/FORWARD"
4455 LET desc$ = "B/FORWARD"
4456 LET refno = 0
4458 WRITE #2, dat$, desc$, trans$, debit, Credit, incometype, refno, account$, weekno
4460 WHILE NOT (EOF(1))
4470 GOSUB readtr
4510 LET Spare = oldbalance + Credit - debit
4520 LET oldbalance = Spare
4540 WRITE #2, dat$, desc$, trans$, debit, Credit, incometype, refno, account$, weekno
4550 WEND
4560 CLOSE #1
4570 CLOSE #2
4571 KILL OLDFILE$
4572 NAME File$ AS OLDFILE$
4575 NAME "NEWTRANS" AS File$
4580 RETURN


4600 REM display selected records
	LET tflag = 0: LET totcred = 0: LET totdeb = 0: LET totIVA = 0
	LET drawing1 = 0: LET drawing2 = 0
	LET bftypeinterest = 0: LET bfcred = 0: LET bfdeb = 0: LET bfIVA = 0
	LET typeinterest = 0: LET typecash = 0: LET typechq = 0: LET typecr = 0

	LET month = VAL(MID$(realdate$, 4, 2))
	GOSUB datetran
	LOCATE 23, 26: PRINT MID$(realdate$, 4, 2)
	LOCATE 23, 1: INPUT "Enter month(mm)(99=all): ", month
	IF month = 0 THEN LET month = VAL(MID$(realdate$, 4, 2))
	GOSUB datetran
	LOCATE 23, 58: PRINT RIGHT$(realdate$, 4)
	LOCATE 23, 30: INPUT "Enter year(yyyy)(9999=all): ", year$
	IF year$ = "" THEN LET year$ = RIGHT$(realdate$, 4)
	LOCATE 23, 65: INPUT "Print (Y/N)", print$
	IF print$ = "y" THEN LET print$ = "Y"
	IF print$ <> "Y" THEN LET print$ = "N"
	REM IF print$ = "Y" THEN GOTO monthst: REM print month
	GOSUB 490: REM  do headings
	GOSUB sel1: REM show records
	REM LOCATE 2, 70: PRINT "        "
	GOTO 4020: REM wait for end key


sel1:   LOCATE 3, 1
	LET lcount = 0
	LET SCOUNT = 0: REM screen count=15 line counts
	OPEN "I", 1, File$
	WHILE NOT (EOF(1))
	GOSUB readtr
	IF accflag$ <> "Y" THEN GOTO wend1
	IF menu = 1 AND VAL(LEFT$(trans$, 2)) = 10 THEN GOTO wend1: REM do not report Misc under food
	IF menu = 10 AND VAL(LEFT$(trans$, 2)) = 10 THEN GOTO skptra
	IF VAL(LEFT$(trans$, 1)) = menu AND menu <= 10 THEN GOTO skptra
	IF menu = 18 GOTO skptra
	IF VAL(LEFT$(trans$, 4)) <> menu THEN GOTO wend1

skptra: GOSUB iveryr
	IF ivymon > month THEN GOTO wend1: REM Don't add
	IF ivflag <> 1 THEN GOTO tstyr: REM test year
	LET ivflag = 0
	IF VAL(RIGHT$(dat$, 4)) = ivyr - 1 THEN GOTO addyr ELSE GOTO wend1
tstyr:  IF RIGHT$(dat$, 4) <> year$ AND year$ <> "9999" THEN GOTO wend1
	IF month > 12 AND month <> 99 AND ivymon < 13 THEN GOTO wend1
addyr:  IF menu = 3 AND LEFT$(trans$, 3) = "301" THEN LET drawing1 = drawing1 + Credit - debit
	IF menu = 3 AND LEFT$(trans$, 3) = "302" THEN LET drawing2 = drawing2 + Credit - debit
	IF VAL(MID$(dat$, 4, 2)) = month OR month = 99 THEN GOTO sel2: REM Add and Display
	LET bfcred = bfcred + Credit: LET bfdeb = bfdeb - debit: LET bfIVA = bfIVA + IVA: REM calc brought forward
	LET bfflag = 1: GOTO wend1

sel2:   REM IF LEFT$(trans$, 1) = "7" GOTO sel3
	LET totcred = totcred + Credit
	LET totdeb = totdeb - debit
	LET totIVA = totIVA + IVA
	
sel3:   IF LEFT$(trans$, 1) = "5" AND incometype = 1 THEN LET typecash = typecash + Credit - debit
	IF LEFT$(trans$, 1) = "5" AND incometype = 2 THEN LET typechq = typechq + Credit - debit
	IF LEFT$(trans$, 1) = "5" AND incometype = 3 THEN LET typecr = typecr + Credit - debit
	IF LEFT$(trans$, 1) = "5" AND incometype = 4 THEN LET typecr = typecr + Credit - debit
       
	GOSUB 6000: REM convert transaction code to description
       
prtln:  IF menu = 18 AND tflag = 0 THEN GOTO wend1:
	LOCATE lcount + 3, 1: PRINT dat$; TAB(11); refno; TAB(19); trans$; TAB(26); incometype$;
	PRINT USING "######.##"; TAB(30); Credit;
	PRINT USING "#######.##"; TAB(39); debit;
	PRINT USING "#####.##"; TAB(50); IVA;
	IF account$ = "1" AND trans$ <> "" THEN LOCATE lcount + 3, 59: PRINT "Hotel"
	IF account$ = "2" AND trans$ <> "" THEN LOCATE lcount + 3, 59: PRINT "House"
	LOCATE lcount + 3, 65: PRINT desc$
	REM IF LEFT$(trans$, 1) <> "3" THEN PRINT desc$
	REM IF LEFT$(trans$, 1) = "3" THEN PRINT who$;
	PRINT "                                                                               "
	IF print$ <> "Y" THEN GOTO prtln1
	PRINT #4, dat$; TAB(11); refno; TAB(19); weekno; TAB(23); trans$; TAB(30); incometype;
	PRINT #4, USING "######.##"; TAB(35); Credit; TAB(44); debit; TAB(56);
	IF account$ = "1" AND trans$ <> "" THEN PRINT #4, "Hotel"; TAB(63);
	IF account$ = "2" AND trans$ <> "" THEN PRINT #4, "House"; TAB(63);
	IF LEFT$(trans$, 1) <> "3" THEN PRINT #4, desc$
	IF LEFT$(trans$, 1) = "3" THEN PRINT #4, who$;
	REM PRINT "                                                                               "
  
prtln1: IF LEFT$(trans$, 1) <> "" THEN GOTO blkref
	LOCATE lcount + 3, 12: PRINT "    ": REM: blank refno & Week No and type.
	IF tflag > 3 GOTO totcrd
	
blkref: lcount = lcount + 1
	IF tflag > 0 GOTO wend1
	REM IF print$ = "Y" GOTO LINE60
	IF lcount < 16 GOTO wend1
	LET lcount = 0
	LOCATE 23, 1: INPUT "Select M for more,Enter for end"; more$
	LET SCOUNT = SCOUNT + 1
	IF more$ = "m" THEN LET more$ = "M"
	IF more$ <> "M" THEN GOTO 30
	GOSUB clrall
	LOCATE 3, 1
wend1:  WEND
	IF lcount < 15 THEN LET lcount = 15
	IF print$ = "Y" AND tflag = 0 THEN PRINT #4, " ": PRINT #4, " "
	IF typeinterest <> 0 AND tflag = 0 THEN : GOSUB totint
	LET tflag = tflag + 1
	IF tflag = 1 THEN LET lcount = lcount + 1: LET trans$ = "": LET incometype = 0: LET desc$ = "Month Total": LET refno = VAL(""): LET dat$ = monthshow$: LET Credit = totcred: LET debit = totdeb: LET IVA = totIVA: GOTO sel3: REM print month totals
	IF tflag = 2 THEN LET trans$ = STR$(menu): LET trans$ = "": LET desc$ = "Brought forward": LET refno = 0: LET dat$ = "": LET Credit = bfcred: LET debit = bfdeb: LET IVA = bfIVA: GOTO sel3: REM print bf totals
	IF tflag = 3 THEN LET trans$ = STR$(menu): LET trans$ = "": LET desc$ = "Year total": LET refno = 0: LET dat$ = " ": LET Credit = bfcred + totcred: LET debit = bfdeb + totdeb: LET IVA = bfIVA + totIVA: GOTO sel3: REM print year to date total
	IF menu = 5 THEN GOTO totcr1
	IF menu <> 3 THEN GOTO totcrd
	LOCATE 23, 35: PRINT ; "Staff"; TAB(45); USING "#######"; drawing2;
	LOCATE 23, 55: PRINT ; "Temp"; TAB(65); USING "#######"; drawing1;
	RETURN

totcr1: LOCATE 23, 25: PRINT ; "Cash"; USING "  #####"; typecash; : PRINT ; " Cheque"; USING "  #####"; typechq; : PRINT ; " Credit"; USING "  #####"; typecr
	RETURN

totcrd: REM:LOCATE 23, 57: PRINT USING "######"; totalcred - totdeb
	RETURN

totint: REM IF tflag = 0 AND menu = 8 AND print$ = "N" THEN LOCATE lcount + 3, 25: PRINT ; "Interest"
	REM IF tflag = 0 AND menu = 8 AND print$ = "Y" THEN print #4, ; TAB(25); "Interest"
	REM IF tflag = 0 AND menu = 5 AND print$ = "N" THEN LOCATE lcount + 3, 23: PRINT ; "Investment"
	REM IF tflag = 0 AND menu = 5 AND print$ = "Y" THEN print #4, ; TAB(23); "Investment"
	RETURN
LINE60: IF lcount < 60 GOTO wend1
	LET lcount = 0
	PRINT #4, CHR$(12)
	GOTO wend1
clear1: IF lcount >= 20 GOTO close1
	PRINT "                                                                               "
	lcount = lcount + 1
	GOTO clear1
close1: LOCATE 23, 1
	PRINT "                                                                             "
	CLOSE #1
	RETURN


iveryr: LET ivymon = VAL(MID$(dat$, 4, 2))
	LET ivyr = VAL(year$)
	RETURN




5000 REM only display searched for records
 GOSUB 5010: REM select and view records
 GOTO 4020

5010 REM check for any record type
LET sdat$ = " ": LET sweek = 0: LET saccount$ = " ": LET sdesc$ = " ": LET strans$ = " ": LET sdebit = 0: LET scredit = 0
LOCATE 23, 1: PRINT "                           "
LOCATE 22, 1: GOSUB 505: REM get headings for entry                                                                "
LOCATE 23, 1: INPUT "", sdat$
LOCATE 23, 12: INPUT "", srefno
LOCATE 23, 19: INPUT "", sweek
LOCATE 23, 22: INPUT "", strans$
LOCATE 23, 30: INPUT "", stype
LOCATE 23, 38: INPUT "", scredit
LOCATE 23, 49: INPUT "", sdebit
LOCATE 23, 57: INPUT "", saccount
LOCATE 23, 63: INPUT "", sdesc$
IF sdesc$ = "" THEN LET sdesc$ = "No Find"
LET sdesc$ = LCASE$(sdesc$)
IF srefno = 0 THEN LET srefno = 99999
IF scredit = 0 THEN LET scredit = 99999
IF sdebit = 0 THEN LET sdebit = 99999
IF sweek = 0 THEN LET sweek = 99
IF stype = 0 THEN LET stype = 9
 GOSUB 490: REM get headings

5540 REM display selected records
5550 LOCATE 3, 1
5560 LET lcount = 0
5570 LET SCOUNT = 0: REM screen count=20 line counts
5580 OPEN "I", 1, File$
5590 WHILE NOT (EOF(1))
5600 GOSUB readtr
	 REM LOCATE 20, 1: PRINT scredit; credit
	 REM LOCATE 21, 1: PRINT sdebit; debit
	 LET desc$ = LCASE$(desc$)
	 IF sdat$ = dat$ GOTO 5620
	 IF saccount$ = account$ GOTO 5620
	 IF LEFT$(desc$, LEN(sdesc$)) = sdesc$ GOTO 5620
	 IF strans$ = trans$ GOTO 5620
	 IF stype = incometype GOTO 5620
	 IF sdebit = debit GOTO 5620
	 IF scredit = Credit GOTO 5620
	 IF srefno = refno GOTO 5620
	 IF sweek = weekno GOTO 5620
	 GOTO 5740


5620 GOSUB 6000
5625 LOCATE lcount + 3, 1: PRINT dat$; TAB(11); refno; TAB(19); weekno; TAB(23); trans$; TAB(30); incometype$; TAB(36); USING "#####.##"; Credit; TAB(46); debit;
     LOCATE lcount + 3, 57: PRINT who$; TAB(65);
     IF trans$ <> "3" THEN PRINT desc$ ELSE PRINT who$;
5630 PRINT "                                                                               "
5640 lcount = lcount + 1
5650 IF lcount < 20 GOTO 5740
5660 LET lcount = 0
5670 LOCATE 23, 1: INPUT "Select M for more,Enter for end"; more$
5700 LET SCOUNT = SCOUNT + 1
5710 IF more$ = "m" THEN LET more$ = "M"
5720 IF more$ <> "M" THEN GOTO 30
5730 LOCATE 3, 1
5740 WEND
5750 IF lcount = 20 GOTO 5790
5760 PRINT "                                                                               "
5770 lcount = lcount + 1
5780 GOTO 5750
5790 LOCATE 23, 1
5800 PRINT "                                                                             "
5810 CLOSE #1
5820 RETURN

	 REM Check entered date is valid
5900     LET DATLEN = LEN(dat$)
	 LET flag$ = "": REM clear error flag
	 LET b = 1
	 GOSUB 1500: REM find length
	 LET D = b - 1: REM length of days
	 LET b = b + 1
	 GOSUB 1500
	 LET MS = D + 2: REM start of month position
	 LET ml = b - MS: REM LENGTH OF MONTH
	 LET YL = DATLEN - b: REM year length
	 IF YL < 1 THEN LET flag$ = "E": RETURN: REM No year entered
	 IF VAL(LEFT$(dat$, D)) > 31 THEN LET flag$ = "E": RETURN: REM day error
	 IF VAL(MID$(dat$, MS, ml)) > 12 THEN LET flag$ = "E": RETURN: REM month error
	 IF VAL(RIGHT$(dat$, YL)) < 2003 THEN LET flag$ = "E": RETURN: REM year error
	 RETURN


REM convert code to description for display
6000 IF account$ = "1" THEN LET who$ = "Hotel"
     IF account$ = "2" THEN LET who$ = "House"
     IF desc$ = "1" THEN LET who$ = "Anne"
     IF desc$ = "2" THEN LET who$ = "Bill"

     LET incometype$ = ""
     IF incometype = 1 THEN LET incometype$ = incometype1$
     IF incometype = 2 THEN LET incometype$ = incometype2$
     IF incometype = 3 THEN LET incometype$ = incometype3$
     IF incometype = 4 THEN LET incometype$ = incometype4$
     IF incometype = 5 THEN LET incometype$ = incometype5$
     IF incometype = 6 THEN LET incometype$ = incometype6$
     IF incometype = 0 THEN LET incometype$ = ""

     
     IF LEFT$(trans$, 1) = "1" THEN LET Translate$ = trans1$
     IF LEFT$(trans$, 1) = "2" THEN LET Translate$ = trans2$
     IF LEFT$(trans$, 1) = "3" THEN LET Translate$ = trans3$
     IF LEFT$(trans$, 1) = "4" THEN LET Translate$ = trans4$
     IF LEFT$(trans$, 1) = "5" THEN LET Translate$ = trans5$
     IF LEFT$(trans$, 1) = "6" THEN LET Translate$ = trans6$
     IF LEFT$(trans$, 1) = "7" THEN LET Translate$ = trans7$
     IF LEFT$(trans$, 1) = "8" THEN LET Translate$ = trans8$
     IF LEFT$(trans$, 1) = "9" THEN LET Translate$ = trans9$
     IF LEFT$(trans$, 2) = "10" THEN LET Translate$ = trans10$
    
     IF trans$ = "" THEN LET Translate$ = trans11$
    
     RETURN

6050 REM Display transaction type for Titles
     IF menu = 1 THEN LET transcode$ = trans1$
     IF menu = 2 THEN LET transcode$ = trans2$
     IF menu = 3 THEN LET transcode$ = trans3$
     IF menu = 4 THEN LET transcode$ = trans4$
     IF menu = 5 THEN LET transcode$ = trans5$
     IF menu = 6 THEN LET transcode$ = trans6$
     IF menu = 7 THEN LET transcode$ = trans7$
     IF menu = 8 THEN LET transcode$ = trans8$
     IF menu = 9 THEN LET transcode$ = trans9$
     IF menu = 10 THEN LET transcode$ = trans10$
    
     IF menu = 101 THEN LET transcode$ = trans101$
     IF menu = 102 THEN LET transcode$ = trans102$
     IF menu = 103 THEN LET transcode$ = trans103$
     IF menu = 104 THEN LET transcode$ = trans104$
     IF menu = 105 THEN LET transcode$ = trans105$
     IF menu = 106 THEN LET transcode$ = trans106$
     IF menu = 107 THEN LET transcode$ = trans107$
    
     IF menu = 201 THEN LET transcode$ = trans201$
     IF menu = 202 THEN LET transcode$ = trans202$
     IF menu = 203 THEN LET transcode$ = trans203$
     IF menu = 204 THEN LET transcode$ = trans204$
    
     IF menu = 301 THEN LET transcode$ = trans301$
     IF menu = 302 THEN LET transcode$ = trans302$
     IF menu = 303 THEN LET transcode$ = trans303$
    
     IF menu = 501 THEN LET transcode$ = trans501$
     IF menu = 502 THEN LET transcode$ = trans502$
     IF menu = 503 THEN LET transcode$ = trans503$
     IF menu = 504 THEN LET transcode$ = trans504$
     IF menu = 505 THEN LET transcode$ = trans505$
     IF menu = 506 THEN LET transcode$ = trans506$
     IF menu = 507 THEN LET transcode$ = trans507$
     IF menu = 508 THEN LET transcode$ = trans508$
     IF menu = 509 THEN LET transcode$ = trans509$
     IF menu = 510 THEN LET transcode$ = trans510$
     IF menu = 511 THEN LET transcode$ = trans511$
    
     IF menu = 601 THEN LET transcode$ = trans601$
     IF menu = 602 THEN LET transcode$ = trans602$
     IF menu = 603 THEN LET transcode$ = trans603$
    
     IF menu = 701 THEN LET transcode$ = trans701$
     IF menu = 702 THEN LET transcode$ = trans702$
     IF menu = 703 THEN LET transcode$ = trans703$
     IF menu = 704 THEN LET transcode$ = trans704$
     IF menu = 705 THEN LET transcode$ = trans705$
    
     IF menu = 801 THEN LET transcode$ = trans801$
     IF menu = 802 THEN LET transcode$ = trans802$
     IF menu = 803 THEN LET transcode$ = trans803$
    
     IF menu = 901 THEN LET transcode$ = trans901$
     IF menu = 902 THEN LET transcode$ = trans902$
     IF menu = 903 THEN LET transcode$ = trans903$
     IF menu = 904 THEN LET transcode$ = trans904$
     IF menu = 905 THEN LET transcode$ = trans905$
     IF menu = 906 THEN LET transcode$ = trans906$
     IF menu = 907 THEN LET transcode$ = trans907$
     IF menu = 908 THEN LET transcode$ = trans908$
     IF menu = 909 THEN LET transcode$ = trans909$
     IF menu = 910 THEN LET transcode$ = trans910$


     IF menu = 951 THEN LET transcode$ = trans951$
     IF menu = 952 THEN LET transcode$ = trans952$
     IF menu = 953 THEN LET transcode$ = trans953$
     IF menu = 954 THEN LET transcode$ = trans954$
     IF menu = 955 THEN LET transcode$ = trans955$
     IF menu = 956 THEN LET transcode$ = trans956$
     IF menu = 957 THEN LET transcode$ = trans957$
     IF menu = 958 THEN LET transcode$ = trans958$
     IF menu = 959 THEN LET transcode$ = trans959$
     IF menu = 960 THEN LET transcode$ = trans960$
     IF menu = 961 THEN LET transcode$ = trans961$
     IF menu = 962 THEN LET transcode$ = trans962$
     IF menu = 963 THEN LET transcode$ = trans963$
     IF menu = 964 THEN LET transcode$ = trans964$
    
     IF menu = 1001 THEN LET transcode$ = trans1001$
     IF menu = 1002 THEN LET transcode$ = trans1002$
     IF menu = 1003 THEN LET transcode$ = trans1003$
     IF menu = 1004 THEN LET transcode$ = trans1004$
     IF menu = 1005 THEN LET transcode$ = trans1005$
     IF menu = 1006 THEN LET transcode$ = trans1006$
     IF menu = 1007 THEN LET transcode$ = trans1007$
     IF menu = 1008 THEN LET transcode$ = trans1008$
     IF menu = 1009 THEN LET transcode$ = trans1009$
    
     RETURN

REM display/print month/year totals/transactions
6100    LET month = 0
	LET week = 0
	IF menu = 20 GOTO enyear
	
       
	LET month = VAL(MID$(realdate$, 4, 2))
	GOSUB datetran
	LOCATE 23, 30: PRINT MID$(realdate$, 4, 2)
	LOCATE 23, 13: INPUT "Enter month(mm): ", month
	IF month = 0 THEN LET month = VAL(MID$(realdate$, 4, 2))
	GOSUB datetran: GOTO enyear

enweek: LOCATE 23, 31: PRINT ; weekno
	LOCATE 23, 13: INPUT "Enter Week no(ww): ", week
	IF week = 0 THEN LET week = weekno

enyear: LOCATE 23, 51: PRINT RIGHT$(realdate$, 4)
	LOCATE 23, 35: INPUT "Enter year(yy): ", year$
	IF year$ = "" THEN LET year$ = RIGHT$(realdate$, 4)
	IF menu <= 10 GOTO monthst
	REM IF menu <> 18 THEN GOTO prtyn: REM check for monthprint
monthst: REM LET print$ = "Y"
	REM GOSUB printmonthheads
	REM GOTO clrtot

prtyn:  LOCATE 23, 56: INPUT "Print (Y/N)", print$
	IF print$ = "y" THEN LET print$ = "Y"

clrtot: LOCATE 3, 1
	LET lcount = 0
	LET SCOUNT = 0: REM screen count=15 line counts
	IF menu = 20 GOTO monthyear
loopyr: LET payments = 0: LET cashhand = 0: LET trans1 = 0: LET trans2 = 0: LET trans3 = 0: LET trans4 = 0: LET trans5 = 0: LET trans6 = 0: LET trans7 = 0: LET trans8 = 0: LET trans9 = 0: LET trans10 = 0
	OPEN "I", 1, File$
	WHILE NOT (EOF(1))
	GOSUB readtr
	IF accflag$ <> "Y" THEN GOTO wend2
	

datestart: LET b = 1
datest1: IF MID$(dat$, b, 1) = "/" OR MID$(dat$, b, 1) = "-" OR MID$(dat$, b, 1) = "" THEN GOTO datest2
	 LET b = b + 1: GOTO datest1
datest2: LET MS = b + 1: REM start of month
datest3: LET b = b + 1
	 IF MID$(dat$, b, 1) <> "/" AND MID$(dat$, b, 1) <> "-" AND MID$(dat$, b, 1) <> "" THEN GOTO datest3
	 LET ml = b - MS: REM LENGTH OF MONTH
	 REM:IF menu = 20 GOTO year
	 IF month <> VAL(MID$(dat$, MS, ml)) THEN GOTO wend2

year:   IF RIGHT$(year$, 4) <> RIGHT$(dat$, 4) THEN GOTO wend2
	IF menu > 10 THEN GOTO norm
	IF menu <> VAL(trans$) GOTO wend2
	  
norm:   LET ivatot = ivatot + IVA
	IF LEFT$(trans$, 2) = "10" THEN LET trans10 = trans10 + Credit - debit: GOTO endnorm
	IF LEFT$(trans$, 1) = "1" THEN LET trans1 = trans1 + Credit - debit: GOTO endnorm
	IF LEFT$(trans$, 1) = "2" THEN LET trans2 = trans2 + Credit - debit: GOTO endnorm
	IF LEFT$(trans$, 1) = "3" THEN LET trans3 = trans3 + Credit - debit: GOTO endnorm
	IF LEFT$(trans$, 1) = "4" THEN LET trans4 = trans4 + Credit - debit: GOTO endnorm
	IF LEFT$(trans$, 1) = "5" THEN LET trans5 = trans5 + Credit - debit: GOTO endnorm: REM income
	IF LEFT$(trans$, 1) = "6" THEN LET trans6 = trans6 + Credit - debit: GOTO endnorm
	IF LEFT$(trans$, 1) = "7" THEN LET trans7 = trans7 + Credit - debit: GOSUB cashvisa: GOTO endnorm
	IF LEFT$(trans$, 1) = "8" THEN LET trans8 = trans8 + Credit - debit: GOSUB cashvisa: GOTO endnorm
	IF LEFT$(trans$, 1) = "9" THEN LET trans9 = trans9 + Credit - debit: REM House Maint.
	
endnorm: REM IF VAL(Trans$) = menu THEN GOSUB prtran
	 

wend2:  WEND
	
	

tottran: LET transtot = trans1 + trans2 + trans3 + trans4 + trans5 + trans6 + trans7 + trans9 + trans10
	IF menu = 20 THEN RETURN: REM do 12 months in year
	IF menu <= 10 GOTO tottrans

Totals: LOCATE lcount + 2, 1: PRINT "P/L for "; TAB(9); monthshow$; " "; year$;
	IF menu <> 20 GOTO totals1
	LOCATE lcount + 2, 1: PRINT "P/L for Year to Date"; TAB(23); year$;
	IF nonrec$ = "Y" THEN PRINT TAB(50); "Non-recurring";
	IF account = 1 THEN PRINT ; TAB(67); "Account:"; " Hotel"
	IF account = 2 THEN PRINT ; TAB(67); "Account:"; " House"
	IF account = 0 THEN PRINT ; TAB(67); "Account:"; " Total"
	
       
	IF print$ <> "Y" THEN GOTO total1
	PRINT #4, title$; realdate$
	IF menu = 20 THEN PRINT #4, "P/L for Year to Date"; TAB(23); year$;
	IF nonrec$ = "Y" THEN PRINT #4, TAB(50); "Non-recurring"; ,
	IF account = 1 THEN PRINT #4, TAB(67); "Account:"; " Hotel"
	IF account = 2 THEN PRINT #4, TAB(67); "Account:"; " House"
	IF account = 0 THEN PRINT #4, TAB(67); "Account:"; " Total"
	

total1: GOSUB showtit
	IF menu = 20 THEN RETURN
total2: GOSUB showlin
       
total2a: IF menu = 20 OR menu = 17 THEN LET lcount = lcount + 1: RETURN:
	GOTO totals1

REM: total3: LOCATE lcount + 3, 1: PRINT "Cash in hand "; TAB(15);
	LOCATE lcount + 4, 1: PRINT USING "#####.##"; tranyr9 + tranyr8 + tranyr7 + tranyr5 + tranyr4 + tranyr3;
	PRINT "                            "
	IF print$ <> "Y" GOTO total5
	PRINT #4, "Cash in hand "; TAB(15);
	PRINT #4, USING "#####.##"; tranyr9 + tranyr8 + tranyr7 + tranyr5 + tranyr4 + tranyr3;
	PRINT #4, "  "

total5: LET cashhandyr = 0: LET tranyrp = 0:  LET tranyr1 = 0: LET tranyr2 = 0: LET tranyr3 = 0: LET tranyr4 = 0: LET tranyr5 = 0: LET tranyr6 = 0: LET tranyr7 = 0: LET tranyr8 = 0: LET tranyr9 = 0: LET tranyr10 = 0: LET tranyrtot = 0
	LET lcount = lcount + 2


totals1: IF menu = 20 GOTO cntadd
	LOCATE lcount + 2, 1: PRINT "P/L for "; TAB(9); monthshow$; " "; year$;
	IF account = 1 THEN PRINT ; TAB(67); "Account:"; " Hotel"
	IF account = 2 THEN PRINT ; TAB(67); "Account:"; " House"
	IF account = 0 THEN PRINT ; TAB(67); "Account:"; " Total"
	LET monthshow$ = ""
	GOSUB showtit
	GOSUB showlin
	IF print$ <> "Y" THEN GOTO cntadd
	PRINT #4, title$; realdate$
	IF menu <> 18 THEN PRINT #4, "P/L for "; TAB(9); monthshow$; " "; year$;
	IF account = 1 THEN PRINT #4, TAB(67); "Account:"; " Hotel"
	IF account = 2 THEN PRINT #4, TAB(67); "Account:"; " House"
	IF account = 0 THEN PRINT #4, TAB(67); "Account:"; " Total"
	PRINT #4, TAB(4); trans1$; TAB(10); trans2$; TAB(21); trans5$; TAB(30); trans6$; TAB(40); trans7$; TAB(49); trans4$; TAB(58); trans9$; TAB(67); trans10$; TAB(75); "Profit"
	PRINT #4, USING "#####.##"; trans1; TAB(9); trans2; TAB(18);
	PRINT #4, USING "######.##"; trans5;
	PRINT #4, USING "#####.##"; TAB(28); trans6; TAB(38); trans7; trans4;
	PRINT #4, USING "######.##"; TAB(54); trans9; trans10; TAB(72); transtot

cntadd: LET lcount = lcount + 3
clear2: IF lcount > 20 GOTO close2
	PRINT "                                                                               "
	lcount = lcount + 1
	GOTO clear2
close2: LOCATE 23, 1
	PRINT "                                                                             "
	GOTO 4020
       
tottrans: LET transtot = transtot + trans9
	  PRINT #4, TAB(50); " "
	  PRINT #4, TAB(1); "Total "; Translate$; TAB(23);
	  PRINT #4, USING "#####.##"; transtot
	  GOTO 4030

REM prtran: GOSUB 6000: REM translate transaction code
	GOSUB printit
	RETURN



monthyear: GOSUB clrall: REM clear screen
	   GOSUB Totals
	   LET month = 1
	   LET year20 = VAL(year$)
	   LET lcount = lcount + 1
monyr1:    IF month = 13 THEN LET month = month - 12: LET year$ = STR$(year20 + 1)
	   GOSUB datetran
	   GOSUB loopyr
	   LET month = month + 1
monyr2:    GOSUB total2
	   GOSUB monyrtot
	   IF month = 13 GOTO monyrend
	   CLOSE #1
	   GOTO monyr1

monyrtot:  LET tranyr1 = tranyr1 + trans1
	   LET tranyr2 = tranyr2 + trans2
	   LET tranyr3 = tranyr3 + trans3
	   LET tranyr4 = tranyr4 + trans4
	   LET tranyr5 = tranyr5 + trans5
	   LET tranyr6 = tranyr6 + trans6
	   LET tranyr7 = tranyr7 + trans7
	   REM LET tranyr8 = tranyr8 + trans8
	   LET tranyr9 = tranyr9 + trans9
	   LET tranyr10 = tranyr10 + trans10
	   
	   LET cashhandyr = cashhandyr + cashhand
	   LET tranyrtot = tranyrtot + transtot
	   RETURN


pcash:     LET trans4 = trans4 + Credit - debit
	   REM:LET cashhand = cashhand - debit: REM do not add home food to cash in hand
cashvisa:  REM:IF incometype = 1 THEN LET trans9 = trans9 - credit + debit: REM visa also= cash in
	   RETURN







monyrend:  LET endyrflag = n
	   LET lcount = 16
	   LOCATE lcount + 3, 3: REM PRINT "Tot";
	   LET monthshow$ = "Tot"
	   LET trans1 = tranyr1
	   LET trans2 = tranyr2
	   LET trans3 = tranyr3
	   LET trans4 = tranyr4
	   LET trans5 = tranyr5
	   LET trans6 = tranyr6
	   LET trans7 = tranyr7
	   LET trans9 = tranyr9
	   LET trans10 = tranyr10
	   LET transtot = tranyrtot
	   GOSUB showlin
monyrend1: GOTO total5


datetran:       IF month = 1 THEN LET monthshow$ = "Jan": RETURN
		IF month = 2 THEN LET monthshow$ = "Feb": RETURN
		IF month = 3 THEN LET monthshow$ = "Mar": RETURN
		IF month = 4 THEN LET monthshow$ = "Apr": RETURN
		IF month = 5 THEN LET monthshow$ = "May": RETURN
		IF month = 6 THEN LET monthshow$ = "Jun": RETURN
		IF month = 7 THEN LET monthshow$ = "Jul": RETURN
		IF month = 8 THEN LET monthshow$ = "Aug": RETURN
		IF month = 9 THEN LET monthshow$ = "Sep": RETURN
		IF month = 10 THEN LET monthshow$ = "Oct": RETURN
		IF month = 11 THEN LET monthshow$ = "Nov": RETURN
		IF month = 12 THEN LET monthshow$ = "Dec": RETURN
		IF month = 99 THEN LET monthshow$ = "All": RETURN
		RETURN
       

printmonthheads: PRINT #4, title$;
		PRINT #4, TAB(1); "Transactions for "; TAB(19); monthshow$; " "; year$; TAB(65); realdate$
		PRINT #4, "----------------------------"; TAB(65); "----------"
		PRINT #4, " "
		PRINT #4, "Date        Ref.No.    Trans.       Credit     Debit   Description"
		PRINT #4, "--------------------------------------------------------------------------"
		GOSUB countskip
		GOSUB countskip
		GOSUB countskip
		GOSUB countskip
		RETURN


prtsel:         OPEN "I", 1, File$
		WHILE NOT (EOF(1))
		INPUT #1, dat$, desc$, trans$, debit, Credit, incometype, refno, account$
		IF VAL(trans$) <> menu THEN GOTO selwend
		GOSUB 6000
		GOSUB printit
selwend:        WEND
selclose:       LOCATE 23, 1
		PRINT "                                                                             "
		CLOSE #1
		RETURN

printit:        PRINT #4, dat$; TAB(13); refno; TAB(24); Translate$;
		PRINT #4, USING "#####.##"; TAB(35); Credit; TAB(45); debit; TAB(56);
		IF trans$ <> "3" THEN PRINT #4, desc$ ELSE PRINT #4, who$;
countskip:      LET prcount = prcount + 1
		IF prcount < 60 THEN RETURN
		PRINT #4, CHR$(12)
		LET prcount = 0
		RETURN




roomavg: OPEN "I", 1, File$
	 OPEN "o", 2, "newtrans"
	 WHILE NOT (EOF(1))
	 GOSUB readtr
	 IF VAL(trans$) <> 5 THEN GOTO mel1: REM only use room trans
	 REM:IF LEFT$(desc$, 1) <> "R" THEN GOTO mel1: REM only Restaurant items
	 WRITE #2, dat$, desc$, trans$, debit, Credit, incometype, refno, account$, weekno
mel1:   WEND
	 CLOSE #1
	 CLOSE #2
	 LET lcount = 0
	 LET room1 = 0: LET room2 = 0: LET room3 = 0: LET room4 = 0: LET room5 = 0
	 LET room6 = 0: LET room7 = 0: LET room8 = 0: LET room9 = 0: LET room10 = 0
	 LET room11 = 0: LET room12 = 0: LET noroom = 0
	
	 LET room1no = 0: LET room2no = 0: LET room3no = 0: LET room4no = 0: LET room5no = 0
	 LET room6no = 0: LET room7no = 0: LET room8no = 0: LET room9no = 0: LET room10no = 0
	 LET room11no = 0: LET room12no = 0: LET noroomno = 0
	
	
	 OPEN "I", 2, "newtrans"
	 WHILE NOT (EOF(2))
	 INPUT #2, dat$, desc$, trans$, debit, Credit, incometype, refno, account$, weekno
	 IF desc$ = room1$ THEN LET room1 = room1 + Credit - debit: LET room1no = room1no + 1: GOTO mel2
	 IF desc$ = room2$ THEN LET room2 = room2 + Credit - debit: LET room2no = room2no + 1: GOTO mel2
	 IF desc$ = room3$ THEN LET room3 = room3 + Credit - debit: LET room3no = room3no + 1: GOTO mel2
	 IF desc$ = room4$ THEN LET room4 = room4 + Credit - debit: LET room4no = room4no + 1: GOTO mel2
	 IF desc$ = room5$ THEN LET room5 = room5 + Credit - debit: LET room5no = room5no + 1: GOTO mel2
	 IF desc$ = room6$ THEN LET room6 = room6 + Credit - debit: LET room6no = room6no + 1: GOTO mel2
	 IF desc$ = room7$ THEN LET room7 = room7 + Credit - debit: LET room7no = room7no + 1: GOTO mel2
	 IF desc$ = room8$ THEN LET room8 = room8 + Credit - debit: LET room8no = room8no + 1: GOTO mel2
	 IF desc$ = room9$ THEN LET room9 = room9 + Credit - debit: LET room9no = room9no + 1: GOTO mel2
	 IF desc$ = room10$ THEN LET room10 = room10 + Credit - debit: LET room10no = room10no + 1: GOTO mel2
	 IF desc$ = room11$ THEN LET room11 = room11 + Credit - debit: LET room11no = room11no + 1: GOTO mel2
	 IF desc$ = room12$ THEN LET room12 = room12 + Credit - debit: LET room12no = room12no + 1: GOTO mel2
	 LET noroom = noroom + Credit - debit: LET noroomno = noroomno + 1
mel2:    WEND
	 CLOSE #2
metits:  CLS
	 PRINT title$; realdate$
	 PRINT "Ave Takings   Freq.  Total      Ave.                                        "
	 LET Room$ = room1$: LET roomno = room1no: LET Room = room1: GOSUB meptot
	 LET Room$ = room2$: LET roomno = room2no: LET Room = room2: GOSUB meptot
	 LET Room$ = room3$: LET roomno = room3no: LET Room = room3: GOSUB meptot
	 LET Room$ = room4$: LET roomno = room4no: LET Room = room4: GOSUB meptot
	 LET Room$ = room5$: LET roomno = room5no: LET Room = room5: GOSUB meptot
	 LET Room$ = room6$: LET roomno = room6no: LET Room = room6: GOSUB meptot
	 LET Room$ = room7$: LET roomno = room7no: LET Room = room7: GOSUB meptot
	 LET Room$ = room8$: LET roomno = room8no: LET Room = room8: GOSUB meptot
	 LET Room$ = room9$: LET roomno = room9no: LET Room = room9: GOSUB meptot
	 LET Room$ = room10$: LET roomno = room10no: LET Room = room10: GOSUB meptot
	 LET Room$ = room11$: LET roomno = room11no: LET Room = room11: GOSUB meptot
	 LET Room$ = room12$: LET roomno = room12no: LET Room = room12: GOSUB meptot
	 LET Room$ = "Invalid": LET roomno = noroomno: LET Room = noroom: GOSUB meptot
       
	
	 LOCATE 23, 1: INPUT "Press Enter to continue"; more$
	 RETURN
meptot:  IF roomno = 0 THEN LOCATE lcount + 4: RETURN
	 LOCATE lcount + 4, 1: PRINT Room$; TAB(15); roomno;
	 PRINT USING "#####.##"; TAB(20); Room; TAB(30); Room / roomno;
	 lcount = lcount + 1
	 RETURN


showtit: LOCATE lcount + 3, 7: PRINT trans1$; "  "; trans2$; TAB(22); trans5$; TAB(31); trans6$; TAB(36); trans7$; TAB(42); trans4$; TAB(51); trans3$; TAB(59); trans9$; TAB(66); trans10$; TAB(75); "Profit"
	 IF print$ = "Y" THEN PRINT #4, TAB(7); trans1$; "  "; trans2$; TAB(22); trans5$; TAB(31); trans6$; TAB(36); trans7$; TAB(42); trans4$; TAB(51); trans3$; TAB(59); trans9$; TAB(66); trans10$; TAB(75); "Profit"
	 RETURN

showlin: LOCATE lcount + 4, 1: PRINT monthshow$;
	PRINT USING "######"; TAB(6); trans1; TAB(14); trans2;
	PRINT USING "######"; TAB(21); trans5;
	PRINT USING "######"; TAB(29); trans6; TAB(36); trans7; TAB(44); trans4;
	PRINT USING "######"; TAB(50); trans3; TAB(58); trans9; TAB(65); trans10; TAB(75); transtot
	IF print$ <> "Y" THEN RETURN
	PRINT #4, monthshow$;
	PRINT #4, USING "######"; TAB(6); trans1; TAB(14); trans2;
	PRINT #4, USING "######"; TAB(21); trans5;
	PRINT #4, USING "######"; TAB(29); trans6; TAB(36); trans7; TAB(44); trans4;
	PRINT #4, USING "######"; TAB(50); trans3; TAB(58); trans9; TAB(65); trans10; TAB(75); transtot
	RETURN



wkshow: LET wktot = 0
	GOSUB wktits
	GOSUB wksh1: REM show records
	LOCATE 2, 70: PRINT "        "
	GOTO 4020: REM wait for end key

wksh1:  LOCATE 3, 1: REM show week totals for rooms
	LET wkdat$ = "0"
	LET lcount = 0
	LET SCOUNT = 0: REM screen count=20 line counts
	OPEN "I", 1, File$
	WHILE NOT (EOF(1))
	GOSUB readtr
	IF wkdat$ = "0" THEN LET wkdat$ = dat$: LET wkday = VAL(LEFT$(dat$, 2)): LET wkmon = VAL(MID$(dat$, 3, 2)): LET wkyear = VAL(RIGHT$(dat$, 2))
	IF VAL(trans$) <> 5 THEN GOTO wkend2: REM only Room items
       
	LET wday = VAL(LEFT$(dat$, 2)): LET wmon = VAL(MID$(dat$, 3, 2)): LET wyear = VAL(RIGHT$(dat$, 2))
	IF (wyear - 95) * 365 + (wmon * 30) + wday >= (wkyear - 95) * 365 + (wkmon * 30) + wkday + 7 THEN LET wkday = wday: LET wkmon = wmon: LET wkyear = wyear: GOTO wkprt
       
	REM:IF (LEFT$(dat$, 2)) = "1/" THEN LET wktot = wktot + credit: LET wkdays = wkdays + 1: GOTO wksh3
	REM:IF wkdays = 1 THEN GOTO wksh2
	REM:IF (LEFT$(dat$, 2)) = "2/" THEN LET wktot = wktot + credit: LET wkdays = wkdays + 1: GOTO wksh3
	REM:IF (LEFT$(dat$, 2)) = "3/" THEN LET wktot = wktot + credit: LET wkdays = wkdays + 1: GOTO wksh3

wksh2:  IF VAL(LEFT$(dat$, 2)) < VAL(wkdat$) + 2 THEN LET wktot = wktot + Credit: LET wkdays = wkdays + 1 ELSE GOTO wkprt
wksh3:  LET wkdat$ = LEFT$(dat$, 2)
	GOTO wkend2

  
wkprt:  IF wktot = 0 THEN GOTO wkend1
	GOSUB wkptot
	IF lcount < 19 GOTO wkend1
	LET lcount = 0
	LOCATE 23, 1: INPUT "Select M for more,Enter for end"; more$
	LET SCOUNT = SCOUNT + 1
	IF more$ = "m" THEN LET more$ = "M"
	IF more$ <> "M" THEN GOTO 30
	LOCATE 3, 1

wkend1: LET wkdat$ = LEFT$(dat$, 2)
	LET wkdays = 0
	GOTO wksh2
wkend2: WEND
	GOSUB wkptot
	lcount = lcount + 1
	GOSUB tottot
	GOTO clear1

wktits: CLS
	PRINT title$; realdate$
	IF print$ <> "Y" GOTO wktit1
	PRINT #4, title$; realdate$
wktit1: PRINT "Room Takings           Rooms Let    Total takings    Ave.per Room"
	RETURN

wkptot: LOCATE lcount + 3, 1: PRINT dat$;
	PRINT USING "###"; TAB(27); wkdays;
	PRINT USING "#####.##"; TAB(38); wktot; TAB(54); wktot / wkdays; TAB(68);
	PRINT "                                                                               "
	LET totdays = totdays + wkdays
	LET totwktot = totwktot + wktot
	LET wkdays = 0
	LET wktot = 0
	lcount = lcount + 1
	RETURN

tottot: LOCATE lcount + 3, 1: PRINT "Totals";
	PRINT USING "####"; TAB(26); totdays;
	PRINT USING "#####.##"; TAB(38); totwktot; TAB(54); totwktot / totdays; TAB(66);
	PRINT "                                                                               "
	LET totdays = 0
	LET totwktot = 0
	lcount = lcount + 1
	RETURN
clr223: LOCATE 22, 1: PRINT "                                                                                "
clr2123: LOCATE 23, 1: PRINT "                                                                                "
clr221: LOCATE 22, 1: PRINT "                                                                                "
	LOCATE 21, 1: PRINT "                                                                                "
       
	RETURN

entE:   LOCATE 23, 1: PRINT "Enter (E) to exit                              "
	RETURN
clrall: LET clearcount = 3
clral1: LOCATE clearcount, 1: PRINT "                                                                                "
	LET clearcount = clearcount + 1
	IF clearcount < 24 THEN GOTO clral1
	RETURN



7140 REM apply standing orders
7160 LOCATE 23, 13: INPUT "Enter month(mm): ", month
7170 LOCATE 23, 33: INPUT "Enter year(yy): ", year
7190 OPEN "a", 1, File$
7200 OPEN "I", 2, FILESO$
7210 OPEN "o", 3, "newsofile"
7220 WHILE NOT (EOF(2))
7230 GOSUB readso
7240 IF SOMONTH <> month GOTO 7410
			REM:7250 LET Y = 0
			REM:7270 LET MONTH$ = STR$(M): REM add 1 to month in dat$
			REM:7280 IF M > 12 THEN LET MONTH$ = "1" ELSE GOTO 7310
			REM:7290 LET Y = VAL(YEAR$) + 1
			REM:7300 LET YEAR$ = STR$(Y): REM ADD 1 TO YEAR
7310 LET dat$ = SODAY$ + "-" + STR$(month) + "-" + RIGHT$(STR$(year), 2)
			REM:7320 LET MONTH$ = STR$(SOMONTH): REM restore actual month
			REM:7325 IF Y <> 0 THEN LET Y = VAL(YEAR$) - 1 ELSE GOTO 7335
			REM:7330 LET YEAR$ = STR$(Y): REM restore actual year
7335 LET incometype = 4: REM Standing Order/DD
7340 LET trans$ = sotrans$: REM:Transaction type
7360 LET Credit = 0
     LET refno = refno + 1
     LET account$ = soaccount$

7380 WRITE #1, dat$, desc$, trans$, debit, Credit, incometype, refno, account$, weekno
7390 LET SOMONTH = SOMONTH + sofreq: REM SET NEXT MONTH
7400 IF SOMONTH > 12 THEN LET SOMONTH = SOMONTH - 12
7410 WRITE #3, SODAY$, desc$, debit, SOMONTH, sofreq, sotrans$, soaccount$
7420 WEND
7430 CLOSE #1
7440 CLOSE #2
7450 CLOSE #3
7460 KILL FILESO$
7470 NAME "newsofile" AS FILESO$
7480 RETURN







REM: Controlling standing orders

7500 CLS
7520 PRINT title$; realdate$
7530 LOCATE 10, 10: PRINT "1. Add standing orders"
7540 LOCATE 12, 10: PRINT "2. Delete standing orders"
7550 LOCATE 14, 10: PRINT "3. Display standing orders file"
7555 LOCATE 16, 10: PRINT "4. Apply standing orders"
7560 LOCATE 22, 10: PRINT "9. Return to main menu"
7570 LOCATE 23, 1: INPUT "Select:  ", somenu$
7580 IF somenu$ = "1" THEN GOSUB 7660
7590 IF somenu$ = "2" THEN GOSUB 8160
7600 IF somenu$ = "3" THEN GOSUB 7870
7605 IF somenu$ = "4" THEN GOSUB 7140
7610 IF somenu$ = "9" THEN RETURN
7620 GOTO 7500
7630
7640
7650 REM add standing orders
7660 GOSUB 7870: REM  title and display records
7670 OPEN "A", 1, FILESO$
7672 LET lcount = lcount + 1
7675 GOTO 7690
7680 LET lcount = 3
7690 LOCATE lcount, 3: INPUT "", SODAY$
7700 IF SODAY$ = "" THEN GOTO 7790
7710 LOCATE lcount, 12: INPUT "", sodebit
     LOCATE lcount, 11: PRINT USING "####.##"; sodebit
7720 LOCATE lcount, 24: INPUT "", SODESC$
7730 LOCATE lcount, 46: INPUT "", SOMONTH
7740 LOCATE lcount, 50: INPUT "", sofreq
7741 LOCATE lcount, 63: INPUT "", sotrans$
7742 LOCATE 23, 1: PRINT "Enter (1) for Cortijo,(2) for Coach House"
     LOCATE lcount, 74: INPUT "", soaccount$
     IF soaccount$ = "1" GOTO 7745
     IF soaccount$ = "2" GOTO 7745
     GOTO 7742
7745 LOCATE 23, 1: PRINT "Depress ENTER to end                              "
7750 GOSUB writeso
7760 LET lcount = lcount + 1
7770 IF lcount = 23 GOTO 7680
7780 GOTO 7690
7790 CLOSE #1
7800 RETURN
7810 CLS
7820 PRINT title$; realdate$
7830 PRINT "  Day     S/O value    Description      Apply month  Freq.month Trans.  Account"
7840 RETURN
7850
7860
7870 REM "DISPLAY S/O RECORDS "
7880 GOSUB 7810: REM Display titles
7890 LET lcount = 2
7900 OPEN "I", 1, FILESO$
7910 WHILE NOT (EOF(1))
7920 INPUT #1, SODAY$, SODESC$, sodebit, SOMONTH, sofreq, sotrans$, soaccount$
7930 lcount = lcount + 1
7940 LOCATE lcount, 3: PRINT ; SODAY$; TAB(10); USING "#####.##"; sodebit; TAB(24); : PRINT USING "\              \"; SODESC$; TAB(40); : PRINT USING "     ##"; SOMONTH; TAB(57); sofreq
7945 IF soaccount$ = "1" THEN LOCATE lcount, 73: PRINT "Hotel"
     IF soaccount$ = "2" THEN LOCATE lcount, 73: PRINT "House"
7950 IF lcount < 22 GOTO 8000
7960 GOSUB 1740
7970 IF more$ = "M" GOTO 8000
7980 IF more$ = "m" GOTO 8000
REM:7990 GOTO 8070
8000 WEND
8010 IF somenu$ = "2" GOTO 8030
8012 LOCATE 23, 1: PRINT "Depress ENTER to end                              "
8015 IF somenu$ = "1" GOTO 8030
8020 LOCATE 23, 1: INPUT "Depress ENTER to end", more$
8030 CLOSE #1
8040 RETURN



8160 REM delete a standing order
8170 GOSUB 7870: REM read and display standing orders
8180 GOSUB 8420: REM display line numbers
8190 LOCATE 23, 1: INPUT "Select line for deletion,or ENTER to end"; DLINE
8200 IF DLINE = 0 THEN RETURN
8210 OPEN "i", 1, FILESO$
8220 OPEN "o", 2, "newsofile"
8230 WHILE NOT (EOF(1))
8240 INPUT #1, SODAY$, desc$, debit, SOMONTH, sofreq, sotrans$, soaccount$
8250 LET DLINE = DLINE - 1
8260 IF DLINE = 0 THEN GOTO 8290
8270 IF SODAY$ = "" GOTO 8290
8280 WRITE #2, SODAY$, desc$, debit, SOMONTH, sofreq, sotrans$, soaccount$
8290 WEND
8300 CLOSE #1
8310 CLOSE #2
8320 KILL FILESO$
8330 NAME "newsofile" AS FILESO$
8340 GOTO 8160
8350
8360 LOCATE 23, 1: PRINT "                                                                              "
8370 LOCATE 3
8375 PRINT "                                                                               "
8380 RETURN

REM: Show line numbers for deletion

8420 LOCATE 2, 72: PRINT "Line no "
8430 LET a = 3: REM line number
8440 LET b = 65: REM column number
8450 LET c = 1: REM display line number
8460 LOCATE a, b: PRINT "          "; c
8470 LET a = a + 1: LET c = c + 1
8480 IF a < 23 GOTO 8460
8490 RETURN


