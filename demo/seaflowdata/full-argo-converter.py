#!/usr/bin/env python
import sys, argparse
import os, datetime
import re
import math
import time
import string
from array import *

# get the args from command line
# set default values for parameters needed by script
timestamp = datetime.datetime.now()
t = timestamp.year, timestamp.month, timestamp.day, timestamp.hour, timestamp.minute, timestamp.second
# get paramets from command line input

parser = argparse.ArgumentParser(description='This is a seaflow database denormalizer, writen by John')
parser.add_argument('-f','--files', help='filename', default='')
parser.add_argument('-m','--month', default='june')
parser.add_argument('-t','--types', default='temp')

args = parser.parse_args()

filenames = args.files
month = args.month
filetypes = args.types.split(',')
outfilename = 'full-' + month + '.csv'

tuples = {}

files = filenames.split(',')
typeidx = 0

for filename in files:
	infile = open(filename, 'r')
	lineno = 0
	curtype = filetypes[typeidx]
	for line in infile:
		if line == '\n':
			continue
		if lineno < 2:
			lineno += 1
			continue
		spl = line.split(',')
		lat = spl[0]
		lon = spl[1]
		for i in range(2,len(spl)):
			depth = (i - 2)*5
			if(depth != 20):
				continue
			val = spl[i].replace('\n','')
			key = (lat,lon,month,depth)
			if key not in tuples:
				tuples[key] = {}
			tuples[key][curtype] = str(val)
			#towrite = str(lat) + ',' + str(lon) + ',' + str(month) + ',' + str(depth) + ',' + str(val) + '\n'
			#outfile.write(towrite)
		lineno += 1
	typeidx+=1

infile.close()

outfile = open(outfilename, 'w')
allkeys = tuples.keys()
allkeys.sort()


for key in allkeys:
	(lat,lon,month,depth) = key
	towrite = str(lat) + "," + str(lon) + "," + str(month) + "," + str(depth)
	for t in filetypes:
		if t not in tuples[key]:
			towrite += ","
		else:
			towrite += "," + tuples[key][t]
	towrite += "\n"
	outfile.write(towrite)

outfile.close()


