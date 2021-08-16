##Ines Fernandez Mini-Project

#be able to add duplicates; pt1 pt2
import time
from datetime import datetime
from graphics import *

def setUp():
    f = open("settingslifeapp.txt", "r")
    content = f.readlines()
    firstLine = content[0]
    firstLine = firstLine.split("/")
    f.close()
    if len(firstLine) < 2:
        print("When was your birthday?")
        month = input("Month: ")
        day = input("Day: ")
        year = input("Year: ")
        birthday = "birthday/" + str(day) + "/" + str(month) + "/" + str(year)
        f = open("settingslifeapp.txt", "a")
        f.write(birthday) #print in first line, push all entries back
        f.write("\n")
        f.close()
        print("Thank you! Birthday has been set up.")
    else:
        override = input("Birthday has already been set up. Override? (yes/no) ")
        if override == "yes":
            print("When was your birthday?")
            month = input("Month: ")
            day = input("Day: ")
            year = input("Year: ")
            birthday = "birthday/" + str(day) + "/" + str(month) + "/" + str(year)
            f = open("settingslifeapp.txt", "a")
            f.write(birthday) #override previous birthday (currently doesnt)
            f.write("\n")
            f.close()
            print("Birthday has been set up.")

##Graphics

def clear(win):
    for item in win.items[:]:
        item.undraw()
    win.update()

def graphicMenu():
    win = GraphWin("This Is My Life", 500, 500) #Setup

    square1 = Rectangle(Point(150, 50), Point(350, 100))
    square1.setFill("turquoise")
    i = Point(250, 75)
    inst1 = Text(i, "Make an Entry")
    square1.draw(win)
    inst1.draw(win)


    square2 = Rectangle(Point(150, 120), Point(350, 170))
    square2.setFill("turquoise")
    i = Point(250, 145)
    inst2 = Text(i, "Live Visualizations")
    square2.draw(win)
    inst2.draw(win)


    square3 = Rectangle(Point(150, 190), Point(350, 240))
    square3.setFill("turquoise")
    i = Point(250, 215)
    inst3 = Text(i, "Stats")
    square3.draw(win)
    inst3.draw(win)

    square4 = Rectangle(Point(150, 260), Point(350, 310))
    square4.setFill("turquoise")
    i = Point(250, 285)
    inst4 = Text(i, "Look into Past")
    square4.draw(win)
    inst4.draw(win)

    square5 = Rectangle(Point(150, 400), Point(350, 450))
    square5.setFill("turquoise")
    i = Point(250, 425)
    inst5 = Text(i, "Settings")
    square5.draw(win)
    inst5.draw(win)

    while 2 == 2:
    
        a = win.getMouse() #What happens when you click
        x = a.getX()
        y = a.getY()

        if x >= 150 and x <= 350:
            if y >= 50 and y <= 100: #box 1, entry (in another win, i think)
                pass
                clear(win)
                i = Point(250, 145)
                inst2 = Text(i, "Entry for:")
                inst2.draw(win)
                
                square = Rectangle(Point(150, 190), Point(350, 240))
                square.setFill("turquoise")
                i = Point(250, 215)
                inst = Text(i, "Today")
                square.draw(win)
                inst.draw(win)

                square = Rectangle(Point(150, 260), Point(350, 310))
                square.setFill("turquoise")
                i = Point(250, 285)
                inst = Text(i, "Another Day")
                square.draw(win)
                inst.draw(win)

                square = Rectangle(Point(10, 10), Point(60, 30))
                square.setFill("turquoise")
                i = Point(35, 20)
                inst = Text(i, "Back")
                square.draw(win)
                inst.draw(win)

                a = win.getMouse()
                x = a.getX()
                y = a.getY()

                if x >= 10 and x <= 60 and y >= 10 and y <= 30: # back
                    graphicMenu() #in the same win
                elif x >= 150 and x <= 350:
                    if y >= 190 and y <= 240: #today
                        entry(datetime.now().date().month, datetime.now().date().day, datetime.now().date().year)
                    elif y >= 260 and y <= 310: #anotherday
                        pass
                    
            elif y >= 120 and y <= 170: #box 2, visuals
                visuals()
            elif y >= 190 and y <= 240: #box 3, stats
                stats(datetime.now().date().month, datetime.now().date().day, datetime.now().date().year)
            elif y >= 260 and y <= 310: #box4, flashbacks
                flashback(datetime.now().date().month, datetime.now().date().day, datetime.now().date().year)
            elif y >= 400 and y <= 450:
                setUp()


def entry(month, day, year):

    text = input("How was your day? ") #say happybirthday if birthday
    #ask to make entry
    #able to add pics, vids, song
    if text != "pass":
        entry = str(month) + "/" + str(day) + "/" + str(year) + "/" + text
        f = open("entries.txt", "a")
        f.write(entry)
        f.write("\n")
        f.close() #can I close it later?
        view = input("Interesting, thanks for telling me.")

                     
def flashback(month, day, year):

    f = open("entries.txt", "r")
    content = f.readlines()
    f.close()

    print()
    time.sleep(0.35)

    oneWeekAgo = weekAgo(month, day, year, content)
    print("One week ago today:") #print day of week?
    print(oneWeekAgo)

    time.sleep(0.35)
    print()

    oneMonthAgo = monthAgo(month, day, year, content)
    print("One month ago today:") 
    print(oneMonthAgo)

    time.sleep(0.35)
    print()

    for n in range (1, len(content)):
        year = year - 1
        oneYearAgo = yearAgo(month, day, year, content)
        if oneYearAgo != "no entry": 
            if n == 1:
                print("One year ago today:")
            else:
                print(n, "years ago today:")
            print(oneYearAgo)



def stats(month, day, year):

    print()
    time.sleep(0.35)
    yearPercent(month, day, year)
    time.sleep(0.35)
    print()
    monthPercent(month, day, year)
    time.sleep(0.35)
    print()
    dayPercent()

def visuals():
    f = open("settingslifeapp.txt", "r")
    content = f.readlines()
    f.close()
    firstLine = content[0]
    firstLine = firstLine.split("/")
    if firstLine[0] != "birthday":
        print("Sorry, birthday has not been set up. Go to settings!")
    else:
        birthMonth, birthDay, birthYear = int(firstLine[1]), int(firstLine[2]), int(firstLine[3])
        weekCount = lifeWeeksCount(datetime.now().date().month, datetime.now().date().day, datetime.now().date().year, birthMonth, birthDay, birthYear)
        lifeWeeksVisual(weekCount) #could be done as a %, or %s could be done as visuals
        

   

##Helper functions

def weekAgo(month, day, year, content): 
    if day >= 8: #if within same month
        dayAgo = day - 7
        monthAgo = month
    else:
        if month > 1: #if not january, previous month
            monthAgo = month - 1
        else:
            monthAgo = 12 #if january, december
        monthAgoDays = dayCountMonth(monthAgo, year) 
        remaining = 7 - day
        dayAgo = monthAgoDays - remaining #day of previous month
    
    selectedEntry = "no entry"
    for n in range(len(content)):
        line = content[n]
        line = line.split("/")
        if line[0] == str(monthAgo) and line[1] == str(dayAgo) and line[2] == str(year):
            selectedEntry = line[3]
    return selectedEntry
        
def monthAgo(month, day, year, content):
    if month > 1:
        monthAgo = month - 1
    else:
        monthAgo = 12
        year = year - 1
    while dayCountMonth(monthAgo, year) < day:
        day = day - 1

    selectedEntry = "no entry"
    for n in range(len(content)):
        line = content[n]
        line = line.split("/")
        if line[0] == str(monthAgo) and line[1] == str(day) and line[2] == str(year):
            selectedEntry = line[3]
    return selectedEntry

def yearAgo(month, day, year, content):
    selectedEntry = "no entry"
    for n in range(len(content)):
        line = content[n]
        line = line.split("/")
        if line[0] == str(month) and line[1] == str(day) and line[2] == str(year):
            selectedEntry = line[3]
    return selectedEntry

def yearPercent(month, day, year):
    totalDays = 0
    for n in range(1, month):
        totalDays = totalDays + dayCountMonth(n, year)
    countDays = totalDays + day
    totalDays = yearDays(year)
    percent = round((countDays/totalDays) * 100)

    print("Year progress:")
    passed = "▓" * (percent//5)
    left = "░" * (20 - (percent//5))
    progressBar = passed + left
    print(progressBar, "{}%".format(percent))
    
    
def monthPercent(month, day, year):
    percent = round(day/dayCountMonth(month, year) * 100)

    print("Month progress:")
    passed = "▓" * (percent//5)
    left = "░" * (20 - (percent//5))
    progressBar = passed + left
    print(progressBar, "{}%".format(percent))

def dayPercent():
    now = datetime.now().time()
    now = str(now)
    now = now.split(":")
    hour = now[0]
    if hour[0] == 0:
        hour = hour[1]
    hour = int(hour)
    minutes = now[1]
    if minutes[0] == 0:
        minutes = minutes[1]
    minutes = int(minutes)
    totalmins = (hour * 60) + minutes
    percent = round(totalmins/1440 * 100)

    print("Day progress:")
    passed = "▓" * (percent//5)
    left = "░" * (20 - (percent//5))
    progressBar = passed + left
    print(progressBar, "{}%".format(percent))

##Life Counts

def lifeYearsCount(month, day, year, birthMonth, birthDay, birthYear):
    countDaysYear = 0
    for n in range(1, month):
        countDaysYear = countDaysYear + dayCountMonth(n, year)
    countDaysYear = countDaysYear + day

    countDaysBirth = 0
    for n in range(1, birthMonth):
        countDaysBirth = countDaysBirth + dayCountMonth(n, year)
    countDaysBirth = countDaysBirth + birthDay
    
    if countDaysYear >= countDaysBirth:
        years = year - birthYear
    else:
        years = year - birthYear - 1
    return years

def lifeWeeksCount(month, day, year, birthMonth, birthDay, birthYear):
    years = lifeYearsCount(month, day, year, birthMonth, birthDay, birthYear)
    
    countDaysBirth = 0
    for n in range(1, birthMonth):
        countDaysBirth = countDaysBirth + dayCountMonth(n, year)
    countDaysBirth = countDaysBirth + birthDay
    weeks = int(countDaysBirth/7)
    weekCount = years*52 + weeks #double check
    
    return weekCount

def lifeMonthsCount(month, day, year, birthMonth, birthDay, birthYear):
    years = lifeYearsCount(month, day, year, birthMonth, birthDay, birthYear)
    monthCount = years*12
    if day >= dayCountMonth(month, year): #count if month 50% over?
        monthCount = monthCount + month
    else:
        monthCount = monthCount + month - 1
    return monthCount
        

#Graphics
    

def lifeWeeksVisual(weekCount):
    win = GraphWin("Your Life in Weeks", 500, 830)
    height = 30
    weeksDrawn = 0
    for n in range(90): #lifespan
        square = Rectangle(Point(5, height), Point(10, height-5))
        for n in range(52):
            square = square.clone()
            square.move(9, 0) #space is due to move before draw, can format like years
            if weeksDrawn < weekCount: #not quite correct
                square.setFill("blue")
            else:
                square.setFill("white")
            square.draw(win)
            weeksDrawn = weeksDrawn + 1
        height = height + 9
#tracer-like function

def lifeMonthsVisual(monthCount):
    pass

def lifeYearsVisual(years):
    win = GraphWin("Your Life in Years", 430, 400)
    height = 50
    yearsDrawn = 0
    for n in range(9): #lifespan
        square = Rectangle(Point(20, height), Point(50, height-30))
        for n in range(10):
            
            if yearsDrawn < years: #not quite correct
                square.setFill("blue")
            else:
                square.setFill("white")
            square.draw(win)
            square = square.clone()
            square.move(40, 0)
            yearsDrawn = yearsDrawn + 1
        height = height + 40


##Helpers for helper functions

def dayCountMonth(month, year):
    if month == 2:
        if year % 400 == 0:
            monthDays = 29
        elif year % 100 == 0:
            monthDays = 28
        elif year % 4 == 0:
            monthDays = 29
        else:
            monthDays = 28
    elif month in [1, 3, 5, 7, 8, 10, 12]:
        monthDays = 31
    else:
        monthDays = 30
    return monthDays

def yearDays(year):
    if year % 400 == 0:
        return 366
    elif year % 100 == 0:
        return 365
    elif year % 4 == 0:
        return 366
    else:
        return 365



    
