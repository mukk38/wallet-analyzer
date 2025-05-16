import {
  PieChart,
  Pie,
  Cell,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";

const COLORS = [
  "#8884d8",
  "#82ca9d",
  "#ffc658",
  "#ff7f50",
  "#00bcd4",
  "#d32f2f",
  "#7e57c2",
  "#4caf50",
];

const TokenUsagePie = ({ data }) => {
  if (!data || data.length === 0) {
    return <div className="text-gray-500">Token kullanım verisi yok.</div>;
  }

  // token bazlı isim + değer (hacim/sayı) göster
  const formattedData = data.map((item) => ({
    name: `${item.tokenSymbol}`,
    value: parseFloat(item.transferCount),
  }));

  return (
    <div className="w-full h-96 bg-white rounded-lg shadow-md p-4">
      <ResponsiveContainer>
        <PieChart>
          <Pie
            data={formattedData}
            dataKey="value"
            nameKey="name"
            cx="50%"
            cy="50%"
            outerRadius={120}
            fill="#8884d8"
            label
          >
            {formattedData.map((_, index) => (
              <Cell key={index} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
          <Legend />
        </PieChart>
      </ResponsiveContainer>
    </div>
  );
};

export default TokenUsagePie;
