define(function(require) {

	var pagedGridModel = function(dataList, recordOptions, defaultOption) {

		var self = this;
	
		self.dataRepo=ko.observableArray(dataList);
		
		self.pagedRecordList = ko.observableArray(dataList);

		self.pageRecordOption = recordOptions;

		self.chosenPageRecord = ko.observable(defaultOption);

		self.nextPageStartIndex = parseInt(self.chosenPageRecord());

		self.prevPageStartIndex = 0;

		self.currentPageNo = ko.observable("1");

		self.noOfPages = ko.computed(function() {
			var noOfPages = Math
					.ceil(self.dataRepo().length / self.chosenPageRecord());
			console.log("data size:" + self.dataRepo().length);
			return noOfPages;
		});

		self.moveToFirstPage = function() {
			self.nextPageStartIndex = parseInt(self.chosenPageRecord());
			self.prevPageStartIndex = 0;
			self.pagedRecordList(dataList.slice(self.prevPageStartIndex, self.nextPageStartIndex));
			self.currentPageNo("1");

		};

		self.moveToLastPage = function() {
			self.nextPageStartIndex =(self.noOfPages()*parseInt(self.chosenPageRecord()));
			console.log("nextPageStartIndex:"+self.nextPageStartIndex);
			self.prevPageStartIndex = self.nextPageStartIndex - self.chosenPageRecord();
			console.log("prevPageStartIndex:"+self.prevPageStartIndex);
			self.pagedRecordList(dataList.slice(self.prevPageStartIndex));
			self.currentPageNo(self.noOfPages());

		};

		self.increamentPageNo = function() {
			var cp = parseInt(self.currentPageNo()) + 1;
			self.currentPageNo(cp);
		};

		self.decreamentPageNo = function() {
			var cp = parseInt(self.currentPageNo()) - 1;
			self.currentPageNo(cp);
		};

		self.moveToNextPage = function() {
			if (self.nextPageStartIndex < dataList.length) {
				self.prevPageStartIndex = self.nextPageStartIndex;
				console.log("Index:" + self.prevPageStartIndex);
				self.nextPageStartIndex = (self.nextPageStartIndex + parseInt(self
						.chosenPageRecord()));
				console.log("offset:" + self.nextPageStartIndex);
				self.pagedRecordList(dataList.slice(self.prevPageStartIndex,
						self.nextPageStartIndex));
				self.increamentPageNo();
			}

		};

		self.moveToPrevPage = function() {
			if (self.prevPageStartIndex - 1 >= 0) {
				self.nextPageStartIndex = self.prevPageStartIndex;
				self.prevPageStartIndex = (self.prevPageStartIndex - parseInt(self
						.chosenPageRecord()));
				console.log("Index:" + self.prevPageStartIndex);
				console.log("offset:" + self.nextPageStartIndex);
				self.pagedRecordList(dataList.slice(self.prevPageStartIndex,
						self.nextPageStartIndex));
				self.decreamentPageNo();
			}
		};

		self.chosenPageRecord.subscribe(function() {
			self.moveToFirstPage();
			self.currentPageNo("1");

		});

	};

	return pagedGridModel;

});