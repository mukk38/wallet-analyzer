import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
} from "recharts";

const FeeChart = ({ data }) => {
  if (!data || data.length === 0) {
    return <div className="text-gray-500">Fee verisi bulunamadı.</div>;
  }

  // timestamp'i okunabilir hale getir
  const formattedData = data.map((entry) => ({
    ...entry,
    date: new Date(parseInt(entry.timeStamp) * 1000).toLocaleDateString(),
    fee: parseFloat(entry.gasPrice),
  }));

  return (
    <div className="w-full h-80 bg-white rounded-lg shadow-md p-4">
      <ResponsiveContainer width="100%" height="100%">
        <LineChart data={formattedData}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="date" />
          <YAxis dataKey="fee" />
          <Tooltip />
          <Line
            type="monotone"
            dataKey="fee"
            stroke="#3b82f6"
            strokeWidth={2}
            dot={false}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default FeeChart;
