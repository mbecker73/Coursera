import glob
import mincemeat

# all data is in text files in data/ directory
text_files = glob.glob('data/*')

# load contents from text files in data folder into dictionary (taken from lecture)
def file_contents(file_name):
	f = open(file_name)
	try:
		return f.read()
	finally:
		f.close()

source = dict((file_name, file_contents(file_name))
			for file_name in text_files)

# lines formatted as paper-id:::author1::author2::.... ::authorN:::title
# Example: 
# journals/cl/SantoNR90:::Michele Di Santo::Libero Nigro::Wilma Russo:::Programmer-Defined Control Abstractions in Modula-2.

# Map function outputs the author as the key, map of words and counts as value
def mapfn(key,value):
	import stopwords

	for line in value.splitlines():
		paper, authors, title = line.split(":::")
		for author in authors.split("::"):
			w={} 
			#loop through words in title, skipping "stopwords"
			for word in title.split():
				if word not in stopwords.allStopWords.keys():
					if word in w: # increase count of word in map if already found 
						w[word] = w[word] + 1
					else:
						w[word] = 1
			yield author, w

# Reduce function adds up counts of all words in each author's map
def reducefn(key,value):
	output={}
	for w in value:
		for word in w:
			if word in output:
				output[word] = output[word] + 1
			else:
				output[word] = w[word]
	return key, output

# Start the server
s = mincemeat.Server()
s.datasource = source
s.mapfn = mapfn
s.reducefn = reducefn

results = s.run_server(password="defaultpass")
print results